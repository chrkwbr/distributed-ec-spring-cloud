package com.example.distributedec.router

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.delay
import java.time.Duration

fun routes() = router {
    GET("/") {
        ok().bodyValue("hello world")
    }
    GET("/user") {
        ok().contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.fromCallable { (User(3, "saburo")) }, User::class.java)
    }
    GET("/users") {
        ok().body(users(), User::class.java)
    }
}

fun users() = Flux.just(
        User(1, "taro"),
        User(2, "jiro"),
        User(3, "saburo")
)

data class User(val id: Long, val name: String)
