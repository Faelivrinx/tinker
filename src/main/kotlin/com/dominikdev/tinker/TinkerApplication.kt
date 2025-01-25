package com.dominikdev.tinker

import com.dominikdev.tinker.security.RSAConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RSAConfig::class)
class TinkerApplication

fun main(args: Array<String>) {
	runApplication<TinkerApplication>(*args)
}
