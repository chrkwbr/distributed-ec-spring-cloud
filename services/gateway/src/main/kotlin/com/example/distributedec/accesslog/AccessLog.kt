package com.example.distributedec.accesslog

data class AccessLog(
        var date: String,
        val method: String,
        val path: String,
        val status: Int,
        val host: String,
        val address: String,
        val elapsed: Long,
        val userAgent: String,
        val referer: String
)
