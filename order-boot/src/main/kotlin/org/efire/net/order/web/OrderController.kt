package org.efire.net.order.web

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.IntegerSerializer
import org.axonframework.commandhandling.gateway.CommandGateway
import org.efire.net.order.backend.CoffeeType
import org.efire.net.order.backend.PlaceOrderCommand
import org.springframework.http.RequestEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * Created by jongtenerife on 12/06/2017.
 */

@RestController
@RequestMapping("/api")
class OrderController(commandGateway: CommandGateway, kafkaTemplate: KafkaTemplate<String, String>) {

    val commandGateway = commandGateway
    val kafkaTemplate = kafkaTemplate

    @PostMapping("/orders")
    fun orderCoffee(@RequestBody order: Map<String, String> )  {

        val placeOrderCommand = PlaceOrderCommand(
                coffeeType(order["type"]!!)!!,
                order["beanOrigin"]!!,
                UUID.randomUUID())
        //return commandGateway.send(placeOrderCommand)
        val message: Message<PlaceOrderCommand> = MessageBuilder
                .withPayload(placeOrderCommand)
                .build()

        kafkaTemplate.send("order-topic", message.toString())
    }


    fun coffeeType(name: String) : CoffeeType? {
        return CoffeeType.values()
                .filter { v -> v.name.equals(name, ignoreCase = true) }
                .firstOrNull()

    }
}