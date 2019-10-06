package com.whaleservice.domain
import org.springframework.beans.factory.annotation.Autowired

data class User(
        var username: String? = "0000",
        var userid: String? = "0000",
        var password: String? = "0000" ,
        var email: String? = "0000"
)