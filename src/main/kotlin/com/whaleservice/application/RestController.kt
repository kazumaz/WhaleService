package com.whaleservice.application

import io.prometheus.client.Counter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


//whale serviceとは直接関係ないが、
//RestAPI関連のテストを実施するためのモジュール。
//本モジュールを消してしまっても問題ない・

@RestController
@RequestMapping("/hello")
class HelloController {
    @RequestMapping(method = [RequestMethod.GET])
    fun hello(): String {
        return "Hello Spring MVC"
    }
}


@RestController
class CounterController {
    companion object {
        private var num = 0
        //prometheusのアクチュエーターに追加するサンプルコード。ただし、うまくいかない。
        //[https://qiita.com/AHA_oretama/items/984f8f63ac95a7192174]を参考に実施。
        //[curl http://localhost:8080/admin/prometheus]で出てこない。
        private var requests: Counter = Counter.build().name("count_requests_total").help("Total count requests.").register()
    }

    @GetMapping("count")
    fun count(): Int {
        requests.inc()
        return ++num
    }
}