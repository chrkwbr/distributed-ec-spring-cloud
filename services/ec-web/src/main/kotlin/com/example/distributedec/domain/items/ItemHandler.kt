package com.example.distributedec.domain.items

class ItemHandler(private val itemRepository: ItemRepository) {
    fun findById(id: Long) = itemRepository.findById(id)

    fun findAll() = itemRepository.findAll()
}
