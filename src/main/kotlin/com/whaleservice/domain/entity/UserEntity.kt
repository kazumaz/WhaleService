package com.whaleservice.domain.entity
import org.springframework.beans.factory.annotation.Autowired

data class UserEntity(
        var username: String? = "default",
        var userid: String? = "default",
        var password: String? = "default" ,
        var email: String? = "default"
)