package com.whaleservice.application

import com.whaleservice.domain.entity.UserEntity
import com.whaleservice.domain.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/players")
class Controller(
        private val userService: UserService
) {

    @GetMapping
    fun index(model: Model): String {
        val userEntityList: MutableList<UserEntity>? = userService.findall()
        model.addAttribute("players", userEntityList)
        return "index"
    }

    @GetMapping("new")
    fun newPlayer(model: Model): String {
        model.addAttribute("userEntity", UserEntity())
        return "new"
    }

    @PostMapping
    fun create(@Valid @ModelAttribute userEntity: UserEntity, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "new"
        userService.registUser(userEntity.userid!!, userEntity.username!!, userEntity.email!!, userEntity.password!!)
        return "redirect:/players"
    }

    //this is used for detail pages
    @GetMapping("{userid}")
    fun show(@PathVariable userid: String, model: Model): String {
        model.addAttribute("player", userService.findOneById(userid));
        return "show"
    }

    //this is used for update pages
    @GetMapping("{userid}/edit")
    fun edit(@PathVariable userid: String, model: Model): String {
        model.addAttribute("player", userService.findOneById(userid));
        return "edit"
    }

    //this is used ny update
    @PutMapping("{userid}")
    fun update(@PathVariable userid: String, @Valid @ModelAttribute userEntity: UserEntity, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "edit"
        userService.registUser(userEntity.userid!!, userEntity.username!!, userEntity.email!!, userEntity.password!!)
        return "redirect:/players"
    }

    @DeleteMapping("{userid}")
    fun destroy(@PathVariable userid: String): String {
        userService.deleteUser(userid)
        return "redirect:/players"
    }
}