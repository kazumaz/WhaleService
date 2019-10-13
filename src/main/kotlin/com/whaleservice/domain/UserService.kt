package com.whaleservice.domain


import com.whaleservice.domain.entity.UserEntity
import com.whaleservice.infrastructure.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userStore: UserRepository

    fun registUser(userid: String, username: String, email: String, password: String) {
        userStore.store(UserEntity(username, userid, password, email))
    }

    fun findall () : MutableList<UserEntity>? {

        var userEntityList : MutableList<UserEntity>? = userStore.getAll()
        return userEntityList
    }

}