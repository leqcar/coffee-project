package org.efire.net.order.domain

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import org.efire.net.order.backend.CancelOrderCommand
import org.efire.net.order.backend.OrderCancelledEvent
import org.efire.net.order.backend.OrderPlacedEvent
import org.efire.net.order.backend.PlaceOrderCommand
import java.util.*

/**
 * Created by jongtenerife on 12/06/2017.
 */
@Aggregate
open class OrderInfo constructor() {

    @AggregateIdentifier
    var orderId: UUID? = null

    @CommandHandler
    constructor(cmd: PlaceOrderCommand) : this() {
        AggregateLifecycle.apply(OrderPlacedEvent(cmd.type, cmd.beanOrigin, cmd.orderId))
    }

    @CommandHandler
    fun handle(cmd: CancelOrderCommand) {
        AggregateLifecycle.apply(OrderCancelledEvent(cmd.reason, cmd.orderId))
    }

    @EventSourcingHandler
    open fun on(e: OrderPlacedEvent) {
        println("Calling OrderPlacedEvent")
        orderId = e.orderId
    }

    @EventSourcingHandler
    open fun on(e: OrderCancelledEvent) {
        orderId = e.orderId
    }
}

