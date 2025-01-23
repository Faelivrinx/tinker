package com.dominikdev.tinker

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestEndpoint {

    @GetMapping("/api/test")
    fun health(): Map<String, String> {
        return mapOf("status" to "OK")
    }
}