package com.example.distributedec.config

import com.example.distributedec.domain.goods.Goods
import com.example.distributedec.domain.goods.GoodsRepository
import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import org.springframework.boot.ApplicationRunner
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.core.DatabaseClient
import reactor.core.publisher.Flux.fromStream
import java.util.stream.Stream

class R2dbcConfig : AbstractR2dbcConfiguration() {
    override fun connectionFactory() = H2ConnectionFactory(H2ConnectionConfiguration
            .builder()
            .url("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
            .build())
}


fun runner(goodsRepository: GoodsRepository, databaseClient: DatabaseClient) = ApplicationRunner {
    val initDb = databaseClient.execute {
        """ CREATE TABLE goods (
                    id BIGINT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL
                );
            """
    }

    val stream = Stream.of(
            Goods(1, "Petros")
    )

    val saveAll = goodsRepository.saveAll(fromStream(stream))

    initDb // initialize the database
            .then()
            .thenMany(saveAll) // then save our Sample Employees
            .subscribe() // execute
}

