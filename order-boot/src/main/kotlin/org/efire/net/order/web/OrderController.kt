package org.efire.net.order.web

import org.axonframework.commandhandling.gateway.CommandGateway
import org.efire.net.order.backend.CoffeeType
import org.efire.net.order.backend.PlaceOrderCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.concurrent.CompletableFuture

/**
 * Created by jongtenerife on 12/06/2017.
 */

@RestController
@RequestMapping("/api")
class OrderController(commandGateway: CommandGateway) {

    val commandGateway = commandGateway

    @PostMapping("/orders")
    fun orderCoffee(@RequestBody order: Map<String, String> ) : CompletableFuture<String> {

        val placeOrderCommand = PlaceOrderCommand(
                coffeeType(order["type"]!!)!!,
                order["beanOrigin"]!!,
                UUID.randomUUID())
        return commandGateway.send(placeOrderCommand)
    }

    fun coffeeType(name: String) : CoffeeType? {
        return CoffeeType.values()
                .filter { v -> v.name.equals(name, ignoreCase = true) }
                .firstOrNull()

    }
}