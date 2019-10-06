package com.whaleservice.domain
import org.springframework.beans.factory.annotation.Autowired

data class User(
        var username: String? = "default",
        var userid: String? = "default",
        var password: String? = "default" ,
        var email: String? = "default"
)