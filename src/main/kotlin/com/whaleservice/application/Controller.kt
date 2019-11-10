package com.whaleservice.application

import com.library.logmessage.log
import com.whaleservice.domain.entity.UserEntity
import com.whaleservice.domain.UserService
import com.whaleservice.infrastructure.InfoMessages
import org.slf4j.Logger
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/players")
class Controller(
        private val userService: UserService,
        val log : Logger
) {

    @GetMapping
    fun index(model: Model): String {
        val userEntityList: MutableList<UserEntity>? = userService.findall()
        model.addAttribute("players", userEntityList)
        InfoMessages.TEST_MESSAGE.log("first message" to "first-desu") { k, v -> log.info(k + v)}
        InfoMessages.TEST_MESSAGE.log("first message" to "first-desu", "second mesasge" to "second-desu") { k, v -> log.error(k + v)}
        return Pages.INDEX.id
    }
    fun test(a: String, b:String): String{
        return a+a+a+b+b
    }

    @GetMapping("new")
    fun newPlayer(model: Model): String {
        model.addAttribute("userEntity", UserEntity())
        return Pages.NEW.id
    }

    @PostMapping
    fun create(@Valid @ModelAttribute userEntity: UserEntity, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "new"
        userService.registUser(userEntity.userid!!, userEntity.username!!, userEntity.email!!, userEntity.password!!)
        return Pages.REDIRECT_PLAYERS.id
    }

    //this is used for detail pages
    @GetMapping("{userid}")
    fun show(@PathVariable userid: String, model: Model): String {
        model.addAttribute("player", userService.findOneById(userid));
        return Pages.SHOW.id
    }

    //this is used for update pages
    @GetMapping("{userid}/edit")
    fun edit(@PathVariable userid: String, model: Model): String {
        model.addAttribute("player", userService.findOneById(userid));
        return Pages.EDIT.id
    }

    //this is used for update
    @PutMapping("{userid}")
    fun update(@PathVariable userid: String, @Valid @ModelAttribute userEntity: UserEntity, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "edit"
        userService.registUser(userEntity.userid!!, userEntity.username!!, userEntity.email!!, userEntity.password!!)
        return Pages.REDIRECT_PLAYERS.id
    }

    @DeleteMapping("{userid}")
    fun destroy(@PathVariable userid: String): String {
        userService.deleteUser(userid)
        return Pages.REDIRECT_PLAYERS.id
    }
}

enum class Pages(val id: String){
    REDIRECT_PLAYERS("redirect:/players"),
    EDIT("edit"),
    SHOW("show"),
    INDEX("index"),
    NEW("new")
}