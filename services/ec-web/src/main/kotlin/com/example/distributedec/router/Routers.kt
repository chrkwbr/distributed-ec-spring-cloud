package com.example.distributedec.router

import com.example.distributedec.domain.items.Item
import com.example.distributedec.domain.items.ItemHandler
import org.springframework.web.reactive.function.server.router

fun routes(itemHandler: ItemHandler) = router {
    GET("/") {
        ok().bodyValue("hello world")
    }
    "/items".nest {
        GET("/") {
            ok().body(itemHandler.findAll(), Item::class.java)
        }
        GET("/{id}") { req ->
            ok().body(itemHandler.findById(req.pathVariable("id").toLong()), Item::class.java)
        }
    }
}

