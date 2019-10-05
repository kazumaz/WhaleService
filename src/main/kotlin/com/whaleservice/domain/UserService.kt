package com.whaleservice.domain


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userStore: UserRepository

    fun registUser(email: Email, password: Password) {

        userStore.store( User("hogehoge", "12345", password, email))
    }

}