package com.example.distributedec.accesslog

import org.apache.commons.logging.LogFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import java.time.OffsetDateTime

class AccessLoggingGatewayFilterFactory : AbstractGatewayFilterFactory<Any>() {
    override fun apply(config: Any?) = GatewayFilter { exchange, chain ->
        val begin = System.nanoTime()
        chain.filter(exchange).doFinally {
            val elapsed = (System.nanoTime() - begin) / 1000000
            val request = exchange.request
            val response = exchange.response
            val now = OffsetDateTime.now()
            val method = request.method
            val path = request.path
            val code = response.statusCode
            val statusCode = code?.value() ?: 0
            val headers = request.headers
            val host = headers.host?.hostString
            val address = request.remoteAddress?.hostString
            val userAgent = headers.getFirst(HttpHeaders.USER_AGENT)
            val referer = headers.getFirst(HttpHeaders.REFERER)
            val accessLog = AccessLog(
                    date = now.toString(),
                    method = method?.toString() ?: "empty method",
                    path = path.value(),
                    status = statusCode,
                    host = host ?: "empty host",
                    address = address ?: "empty address",
                    elapsed = elapsed,
                    userAgent = userAgent ?: "empty user agent",
                    referer = referer ?: "empty referer")
            log.info("$accessLog")
        }
    }

    companion object {
        private val log = LogFactory.getLog(AccessLoggingGatewayFilterFactory.javaClass)
    }
}
