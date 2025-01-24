package com.dominikdev.tinker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RsaConfig::class)
class TinkerApplication

fun main(args: Array<String>) {
	runApplication<TinkerApplication>(*args)
}
