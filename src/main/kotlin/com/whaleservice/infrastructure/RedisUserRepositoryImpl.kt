package com.whaleservice.infrastructure

import com.whaleservice.domain.entity.UserEntity
import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import org.springframework.stereotype.Repository

@Repository
class RedisUserRepositoryImpl(redisProperties: RedisProperties) : UserRepository {

    companion object {
        const val USERNAME_PREFIX = "user:"
        const val USERID = "userid:"
        const val USERID_CONST = "usrid"
        const val USERNAME_EMAIT = "username"
        const val EMAIL_CONST = "email"
        const val PASSWORD_CONST = "password"
    }

    val redisCommands: RedisCommands<String, String>
    val redisConnection: StatefulRedisConnection<String, String>
    var redisClient: RedisClient = RedisClient.create(redisProperties.url)

    init {
        this.redisConnection = redisClient.connect()
        this.redisCommands = redisConnection.sync()
    }

    fun setKey() {
        this.redisCommands.setex("foo", 100, "bar")
    }

    fun closeConnection() {
        this.redisConnection.close()
        this.redisClient.shutdown()
    }

    override fun store(userEntity: UserEntity) {
        this.redisCommands.hmset("${USERNAME_PREFIX}${userEntity.userid}",
                mapOf("username" to userEntity.username, "userid" to userEntity.userid,
                        "email" to userEntity.email.toString(), "password" to userEntity.password.toString()))

    }

//    override fun getAll(): MutableList<User>? {
//        var listUser: MutableList<User>? = mutableListOf()
//
//        var keysList: List<String> = this.redisCommands.keys("*")
//
//        println("キーリスト表示")
//        println(keysList)
//
//        for (item in keysList) {
//            var user: User = User()
//            println(item)
//
//            //各キーで、ハッシュマップを取得する。
//            var userList: MutableMap<String, String>? = this.redisCommands.hgetall(item)
//
//            if (userList != null) { //ハッシュマップが取得できた場合
//
//                var userid: String? = userList.get("userid")
//                if (userid != null) {
//                    user.userid = userid
//                }
//
//                var username: String? = userList.get("username")
//                if (username != null) {
//                    user.username = username
//                }
//
//                var email: String? = userList.get("email")
//                if (userid != null) {
//                    user.email = email
//                }
//
//                var password: String? = userList.get("password")
//                if (userid != null) {
//                    user.password = password
//                }
//
//                listUser?.add(user)
//            }
//        }
//        return listUser
//    }

    override fun getAll(): MutableList<UserEntity>? {
        val listUserEntity: MutableList<UserEntity>? = mutableListOf()
        val keysList: List<String> = this.redisCommands.keys("*")

        for (item in keysList) {
            val userEntity: UserEntity = UserEntity()

            //各キーで、ハッシュマップを取得する。
            val userList: MutableMap<String, String>? = this.redisCommands.hgetall(item)

            if (userList != null) { //ハッシュマップが取得できた場合

                userEntity.userid = checkNotNull(userList["userid"])
                userEntity.username = checkNotNull(userList["username"])
                userEntity.email = checkNotNull(userList["email"])
                userEntity.password = checkNotNull(userList["password"])

                listUserEntity?.add(userEntity)
            }
        }
        return listUserEntity
    }

    override fun findOneById(userid: String): UserEntity {

        val userEntity: UserEntity = UserEntity()
        val getkey: String = "${USERNAME_PREFIX}${userid}"
        val userList: MutableMap<String, String>? = this.redisCommands.hgetall(getkey)

        if (userList != null) { //ハッシュマップが取得できた場合

            userEntity.userid = checkNotNull(userList["userid"])
            userEntity.username = checkNotNull(userList["username"])
            userEntity.email = checkNotNull(userList["email"])
            userEntity.password = checkNotNull(userList["password"])
        }
        return userEntity
    }

    override fun findOneByEmail(email: String): UserEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}