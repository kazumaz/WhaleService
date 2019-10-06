package com.whaleservice.domain


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userStore: UserRepository

    fun registUser(userid: String, username: String, email: String, password: String) {
        userStore.store( User(username, userid, password, email))
    }

    fun findall () : MutableList<User>? {

        var userList : MutableList<User>? = userStore.getAll()
        return userList
    }

}