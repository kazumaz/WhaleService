package com.whaleservice.domain.usecase

import com.whaleservice.domain.Email
import com.whaleservice.domain.Password
import com.whaleservice.domain.User
import com.whaleservice.domain.UserRepository
import com.whaleservice.domain.entity.UserEntity
import com.whaleservice.infrastructure.RedisUserRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired

class UserRegisterUsecase {

    @Autowired
    lateinit var redisRepository: RedisUserRepositoryImpl

    @Autowired
    lateinit var userStore: UserRepository

    fun registUser(email: Email, password: Password) {
        userStore.store(User("hogehoge", "12345", password, email))
    }

    fun checkExistUser(userEntyty: UserEntity): Existence {
        return Existence.EXIST

        }


    enum class Existence {
        EXIST,
        NOT_EXIST
    }
}