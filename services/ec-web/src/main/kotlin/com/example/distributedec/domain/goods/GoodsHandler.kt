package com.example.distributedec.domain.goods

import org.springframework.transaction.annotation.Transactional

@Transactional
class GoodsHandler(private val goodsRepository: GoodsRepository) {
    fun findById(id: Long) = goodsRepository.findById(id)

    fun findAll() = goodsRepository.findAll()
}
