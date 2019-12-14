package com.whaleservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
//import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector

@SpringBootApplication
// 下記はSpring2.0サポートしてなさそう
//@EnablePrometheusEndpoint
//@EnableSpringBootMetricsCollector
class WhaleserviceApplication

fun main(args: Array<String>) {
	runApplication<WhaleserviceApplication>(*args)
}



