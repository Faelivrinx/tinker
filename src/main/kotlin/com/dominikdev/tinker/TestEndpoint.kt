package com.dominikdev.tinker

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestEndpoint {

    @GetMapping("/private")
    fun private(): Map<String, String> {
        return mapOf("message" to "Hello from private endpoint!")
    }

    @GetMapping("/public")
    fun public(): Map<String, String> {
        return mapOf("message" to "Hello from public endpoint!")
    }
}