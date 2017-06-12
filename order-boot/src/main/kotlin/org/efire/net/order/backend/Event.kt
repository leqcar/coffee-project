package org.efire.net.order.backend

import java.time.Instant
import java.util.*

/**
 * Created by jongtenerife on 12/06/2017.
 */

open class CoffeeEvent(instant: Instant = Instant.now())

class OrderPlacedEvent(val type: CoffeeType, val beanOrigin: String, val orderId: UUID) : CoffeeEvent()
class OrderCancelledEvent(val reason: String, val orderId: UUID) : CoffeeEvent()