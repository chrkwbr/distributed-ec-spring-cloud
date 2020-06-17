package com.example.distributedec

import com.example.distributedec.accesslog.AccessLoggingGatewayFilterFactory
import io.micrometer.core.instrument.config.MeterFilter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

@SpringBootApplication
class Gateway

val beans = beans {
    bean<AccessLoggingGatewayFilterFactory>()
    bean("meterFilter") {
        MeterFilter.deny {
            it.getTag("uri")?.startsWith("/actuator") ?: false
        }
    }
}

class BeansInitializer : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(applicationContext: GenericApplicationContext) = beans.initialize(applicationContext)
}

fun main(args: Array<String>) {
    SpringApplicationBuilder(Gateway::class.java)
            .properties("context.initializer.classes=${BeansInitializer::class.java.name}")
            .run(*args)
}
