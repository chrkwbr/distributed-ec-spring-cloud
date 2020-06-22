package com.example.distributedec.domain.goods

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class GoodsHandler {
    fun findById(id: Long) = Mono.just(Goods(id, "mono goods"))

    fun findAll() = Flux.just(
            Goods(1, "taro"),
            Goods(2, "jiro"),
            Goods(3, "saburo")
    )
}
