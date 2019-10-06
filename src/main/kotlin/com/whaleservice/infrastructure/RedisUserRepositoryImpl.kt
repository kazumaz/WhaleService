package com.whaleservice.infrastructure

import com.whaleservice.domain.Email
import com.whaleservice.domain.User
import com.whaleservice.domain.UserRepository
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

    override fun store(user: User) {
//        val nextid = if(user.userid != 0L) user.userid else this.redisCommands.incr("${USERID}")
//        this.redisCommands.hmset("${USERNAME_PREFIX}${user.username}", mapOf("email" to user.email.toString(), "password" to user.password.toString(), "userid" to "${nextid}"))
//        val nextid = 11
//        this.redisCommands.hmset("${USERNAME_PREFIX}${user.username}",
//                mapOf("userid" to user.userid, "username" to user.username,
//                        "email" to user.email.toString(), "password" to user.password.toString(), "userid" to "${nextid}"))
        this.redisCommands.hmset("${USERNAME_PREFIX}${user.username}",
                mapOf("username" to user.username,"userid" to user.userid,
                        "email" to user.email.toString(), "password" to user.password.toString()))

    }

    override fun getAll(): MutableList<User>? {
        var listUser: MutableList<User>? = mutableListOf()

        var keysList: List<String> = this.redisCommands.keys("*")

        println("キーリスト表示")
        println(keysList)

        for (item in keysList) {
            var user: User = User()
            println(item)

            //各キーで、ハッシュマップを取得する。
            var userList: MutableMap<String, String>? = this.redisCommands.hgetall(item)

            if (userList != null) { //ハッシュマップが取得できた場合

                var userid: String? = userList.get("userid")
                if (userid != null) {
                    user.userid = userid
                }

                var username: String? = userList.get("username")
                if (username != null) {
                    user.username = username
                }

                var email: String? = userList.get("email")
                if (userid != null) {
                    user.email = email
                }

                var password: String? = userList.get("password")
                if (userid != null) {
                    user.password = password
                }

                listUser?.add(user)
            }
        }
        return listUser
    }


    override fun findOneById(userid: Long): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override  fun findOneByEmail(email: Email): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}