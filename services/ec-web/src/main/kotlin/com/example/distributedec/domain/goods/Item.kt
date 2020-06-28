package com.example.distributedec.domain.goods

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("items")
data class Item(@Id val id: Long, val name: String)
