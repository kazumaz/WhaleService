package com.whaleservice.infrastructure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "colors")
class PlatformProperties {
    lateinit var red: String
    lateinit var blue: String
}