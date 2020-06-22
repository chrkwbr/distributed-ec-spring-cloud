package com.example.distributedec

import com.example.distributedec.config.R2dbcConfig
import com.example.distributedec.config.runner
import com.example.distributedec.domain.goods.GoodsHandler
import com.example.distributedec.router.routes
import io.micrometer.core.instrument.config.MeterFilter
import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
class EcApplication

val beans = beans {
    bean { R2dbcConfig() }
    bean { ref<R2dbcConfig>().connectionFactory() }
    bean { runner(ref(), ref()) }
    bean { GoodsHandler(ref()) }
    bean { routes(ref()) }
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
    SpringApplicationBuilder()
            .sources(EcApplication::class.java)
            .properties("context.initializer.classes=${BeansInitializer::class.java.name}")
            .web(WebApplicationType.REACTIVE)
            .run(*args)
}
