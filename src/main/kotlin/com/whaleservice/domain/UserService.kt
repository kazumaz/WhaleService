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
        //TODO ユーザーID重複チェク
        userStore.store(UserEntity(username, userid, password, email))
    }

    fun findall () : MutableList<UserEntity>? {
        return userStore.getAll()
    }

    fun findOneById(userid: String) : UserEntity? {
        return userStore.findOneById(userid)
    }

    fun deleteUser(userid: String)  {
        userStore.deleteById(userid)
    }

}