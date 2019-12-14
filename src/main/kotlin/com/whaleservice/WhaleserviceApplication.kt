package com.whaleservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
//import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector

@SpringBootApplication
class WhaleserviceApplication

fun main(args: Array<String>) {
	runApplication<WhaleserviceApplication>(*args)
}



