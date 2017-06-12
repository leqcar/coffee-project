package org.efire.net.order.backend

import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.util.*

/**
 * Created by jongtenerife on 12/06/2017.
 */

data class PlaceOrderCommand(val type: CoffeeType, val beanOrigin: String, @TargetAggregateIdentifier val orderId: UUID)
data class AcceptOrderCommand(@TargetAggregateIdentifier val orderId: UUID)
data class CancelOrderCommand(@TargetAggregateIdentifier val orderId: UUID, val reason: String)
data class StartOrderCommand(val orderId: UUID)
data class FinishOrderCommand(val orderId: UUID)

enum class CoffeeType {
    ESPRESSO, POUR_OVER, FRENCH_PRESS
}

