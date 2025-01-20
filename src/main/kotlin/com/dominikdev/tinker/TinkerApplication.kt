package com.dominikdev.tinker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TinkerApplication

fun main(args: Array<String>) {
	runApplication<TinkerApplication>(*args)
}
