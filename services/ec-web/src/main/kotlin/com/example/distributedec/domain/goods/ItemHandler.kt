package com.example.distributedec.domain.goods

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ItemHandler(private val itemRepository: ItemRepository) {
    fun findById(id: Long) = itemRepository.findById(id)

    fun findAll() = itemRepository.findAll()
}
