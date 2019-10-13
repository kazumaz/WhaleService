package com.whaleservice.application


import com.whaleservice.domain.entity.UserEntity
import com.whaleservice.domain.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import com.whaleservice.infrastructure.RedisUserRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired

@Controller
@RequestMapping("/players")
class PlayerController(private val userService: UserService) {

    @GetMapping
    fun index(model: Model): String {
        var userEntityList: MutableList<UserEntity>? = userService.findall()
        model.addAttribute("players", userEntityList)
        return "index"
    }

    @GetMapping("new")
    fun newPlayer(): String {
        return "new"
    }

    @PostMapping
    fun create(@ModelAttribute userEntity: UserEntity): String {
        if (userEntity.userid != null && userEntity.username != null && userEntity.email != null && userEntity.password != null) {
            userService.registUser(userEntity.userid!!, userEntity.username!!, userEntity.email!!, userEntity.password!!)
        }
        return "redirect:/players"
    }

    //
//        @GetMapping("{id}/edit")
//        // ⑤
//        fun edit(@PathVariable id: Long, model: Model): String {
//            model.addAttribute("player", playerService.findOne(id));
//            return "players/edit"
//        }
//
    @GetMapping("{userid}")
    fun show(@PathVariable userid: String, model: Model): String {
        model.addAttribute("player", userService.findOneById(userid));
        return "show"
    }
//

//
//        @PutMapping("{id}")
    //        fun update(@PathVariable id: Long, @ModelAttribute player: Player): String {
//            playerService.save(player.copy(id = id))
//            return "redirect:/players"
//        }
//
//        @DeleteMapping("{id}")
//        fun destroy(@PathVariable id: Long): String {
//            playerService.delete(id)
//            return "redirect:/players"
//        }
//    }
}