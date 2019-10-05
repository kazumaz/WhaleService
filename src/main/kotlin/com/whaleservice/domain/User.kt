package com.whaleservice.domain
import org.springframework.beans.factory.annotation.Autowired

class User(
        var username: String?,
        var userid: String?,
        val password: String?,
        val email: String?


//        fun toString(): String  {
//    return "Player [username=${username}, userid=${userid}, password=${password1}, email=${email1}]"
//   }

)