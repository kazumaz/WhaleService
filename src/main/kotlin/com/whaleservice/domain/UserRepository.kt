package com.whaleservice.domain

interface UserRepository {
    fun store(user: User)
    fun findOneById(userid: Long): User
    fun findOneByEmail(email: Email): User
    fun getAll(): MutableList<User>?
}