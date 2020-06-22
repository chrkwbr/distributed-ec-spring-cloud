package com.example.distributedec.domain.goods

import org.springframework.data.annotation.Id

data class Goods(@Id val id: Long, val name: String)
