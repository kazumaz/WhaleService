package com.whaleservice.infrastructure

import com.whaleservice.domain.entity.UserEntity

interface UserRepository {
    fun store(userEntity: UserEntity)
    fun findOneById(userid: String): UserEntity
    fun findOneByEmail(email: String): UserEntity
    fun getAll(): MutableList<UserEntity>?
    fun deleteById(userid: String)
}