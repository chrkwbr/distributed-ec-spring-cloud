package com.example.distributedec

import com.example.distributedec.domain.items.ItemHandler
import io.micrometer.core.instrument.config.MeterFilter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

@SpringBootApplication
class EcApplication

val beans = beans {
//    bean { runner(ref(), ref()) }
    bean { ItemHandler(ref()) }
//    bean { routes(ref()) }
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
//    SpringApplicationBuilder()
//            .sources(EcApplication::class.java)
//            .properties("context.initializer.classes=${BeansInitializer::class.java.name}")
//            .web(WebApplicationType.REACTIVE)
//            .run(*args)
    runApplication<EcApplication>(*args)
}
