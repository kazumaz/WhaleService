package com.whaleservice.domain

interface UserRepository {
    fun store(user: User)
    fun findOneById(userid: String): User
    fun findOneByEmail(email: String): User
    fun getAll(): MutableList<User>?
}