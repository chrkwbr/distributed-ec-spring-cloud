package com.example.distributedec.router

import org.springframework.web.reactive.function.server.router

fun routes() = router {
    GET("/") {
        ok().bodyValue("hello world")
    }
    GET("/api") {
        ok().bodyValue("hello api")
    }
}
