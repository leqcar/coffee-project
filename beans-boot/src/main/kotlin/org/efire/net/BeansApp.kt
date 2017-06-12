package org.efire.net

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by jongtenerife on 12/06/2017.
 */

@SpringBootApplication
open class BeansApp

fun main(args: Array<String>) {
    SpringApplication.run(BeansApp::class.java, *args)
}