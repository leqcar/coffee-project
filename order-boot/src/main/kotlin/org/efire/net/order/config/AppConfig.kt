package org.efire.net.order.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.IntegerSerializer
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

/**
 * Created by JONGT on 6/14/2017.
 */

@EnableKafka
open class AppConfig {

    @Bean
    fun kafkaTemplate() : KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }

    fun producerFactory(): ProducerFactory<String, String>? {
        return DefaultKafkaProducerFactory(producerConfigs())
    }

    fun producerConfigs(): MutableMap<String, Any>? {
        val props = mutableMapOf<String, Any>().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer::class.java)
        }
        return props
    }
}