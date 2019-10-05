package com.whaleservice.domain


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userStore: UserRepository

    fun registUser(userid: String, username: String, email: String, password: String) {
        userStore.store( User(userid, username, password, email))
    }

    fun findall () : MutableList<User>? {
        return userStore.getAll()
    }

}