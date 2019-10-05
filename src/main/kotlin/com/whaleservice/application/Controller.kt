package com.whaleservice.application


import com.library.logmessage.log
import com.whaleservice.domain.Email
import com.whaleservice.domain.Password
import com.whaleservice.domain.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import com.whaleservice.infrastructure.FruitProperties
import com.whaleservice.infrastructure.PlatformProperties
import com.whaleservice.infrastructure.RedisUserRepositoryImpl
import com.whaleservice.presentation.Loginform
import org.springframework.beans.factory.annotation.Autowired
import org.slf4j.Logger

@Controller
class HelloController {

        @RestController
        class Controller(
                val colorProperties: PlatformProperties,
                val fruitProperties: FruitProperties
        ) {
            @GetMapping("colors/red")
            fun translateColorToJapanese(): String {
                return colorProperties.red
            }

            @GetMapping("fruits/price/{fruit}")
            fun getFruitPrice(@PathVariable fruit: String): String {
                return "${fruitProperties.prices.get(fruit)}円です"
            }
        }


        @RestController
        @RequestMapping
        class RedisController {



            @Autowired
            lateinit var redisRepository: RedisUserRepositoryImpl

            @Autowired
            lateinit var usermanagement: UserService

            @GetMapping("/kazumaz") //very good method!!
            fun test(): String{

                val email: Email = Email("test")
                val password: Password = Password("12345")
                usermanagement.registUser(email, password)
                return("testtest")
            }

            @GetMapping("/set2")
            fun registUser(@RequestBody loginform: Loginform) {
                usermanagement.registUser(Email(loginform.email), Password(loginform.password))
            }

            @GetMapping("/set")
            fun getTop(): String {
                redisRepository.redisCommands.setex("foo", 100, "bar")
                redisRepository.redisCommands.setex("kazuma", 100, "bar")
                redisRepository.redisCommands.setex("yu", 100, "bar")
                return "test"
                println("testtest")
            }
        }
    }
