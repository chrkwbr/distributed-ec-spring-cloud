package com.example.distributedec.domain.goods

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface GoodsRepository : ReactiveCrudRepository<Goods, Long> {
//    @Query("""
//        select
//          id,
//          name
//        from
//          goods
//        where
//          id = :id
//    """)
//    override fun findById(id: Long): Mono<Goods>
//
//
//    @Query("""
//        select
//          id,
//          name
//        from
//          goods
//    """)
//    override fun findAll(): Flux<Goods>
}
