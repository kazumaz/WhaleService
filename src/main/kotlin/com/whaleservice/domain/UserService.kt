package com.whaleservice.domain


import com.whaleservice.domain.User
import com.whaleservice.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userStore: UserRepository

    fun registUser(email: Email, password: Password) {
        userStore.store( User("hogehoge", 0, password, email))
    }

}