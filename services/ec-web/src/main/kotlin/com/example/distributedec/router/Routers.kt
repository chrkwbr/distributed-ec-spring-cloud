package com.example.distributedec.router

import com.example.distributedec.domain.goods.Goods
import com.example.distributedec.domain.goods.GoodsHandler
import org.springframework.web.reactive.function.server.router

fun routes(goodsHandler: GoodsHandler) = router {
    GET("/") {
        ok().bodyValue("hello world")
    }
    GET("/goods").nest {
        GET("/") {
            ok().body(goodsHandler.findAll(), Goods::class.java)
        }
        GET("/{id}") {req -> 
            ok().body(goodsHandler.findById(req.pathVariable("id").toLong()), Goods::class.java)
        }
    }
}
