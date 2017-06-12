package org.efire.net

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by jongtenerife on 12/06/2017.
 */

@SpringBootApplication
open class OrderApp

fun main(args: Array<String>) {
    SpringApplication.run(OrderApp::class.java, *args)
}