package org.efire.net

import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.efire.net.order.backend.*
import org.efire.net.order.domain.OrderInfo
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by jongtenerife on 12/06/2017.
 */

class OrderAppTest {

    private val fixture: FixtureConfiguration<OrderInfo> = AggregateTestFixture(OrderInfo::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun testOrderPlaced() {
        val orderId = UUID.randomUUID()
        fixture.givenNoPriorActivity()
                .`when`(PlaceOrderCommand(CoffeeType.ESPRESSO, "Columbia", orderId))
                .expectEvents(OrderPlacedEvent(CoffeeType.ESPRESSO, "Columbia", orderId))
    }

    @Test
    fun testOrderCancelled() {
        val orderId = UUID.randomUUID()
        fixture.given(OrderPlacedEvent(CoffeeType.FRENCH_PRESS, "Arabica", orderId))
                .`when`(CancelOrderCommand(orderId, "Slow service"))
                .expectEvents(OrderCancelledEvent("Slow service", orderId))
    }
}
