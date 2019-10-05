package com.whaleservice.domain
import org.springframework.beans.factory.annotation.Autowired

class User(
        val username: String,
        val userid: Long,
        val password: Password,
        val email: Email
)