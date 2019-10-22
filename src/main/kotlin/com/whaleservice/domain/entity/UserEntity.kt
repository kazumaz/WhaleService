package com.whaleservice.domain.entity
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class UserEntity(
        @get:NotEmpty
        @get:Size(max = 20)
        var username: String? = "default",
        @get:NotEmpty
        @get:Size(max = 20)
        var userid: String? = "default",
        @get:Size(max = 20)
        @get:NotEmpty
        var password: String? = "default" ,
        @get:Size(max = 20)
        @get:NotEmpty
        var email: String? = "default"
)