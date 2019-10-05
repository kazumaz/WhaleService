package com.whaleservice.infrastructure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "fruits")
class FruitProperties {
    lateinit var prices: Map<String, Int>
}