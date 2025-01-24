package com.dominikdev.tinker

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthEndpoint(val authService: AuthService) {

    @PostMapping("/token")
    fun health(@RequestBody request: TokenRequestDto): TokenResponseDto {
        return authService.authenticateWithToken(request)
    }

    @GetMapping("/api/hit")
    fun hit(): Map<String, String> {
        return mapOf("message" to "Hit!")
    }
}

data class TokenRequestDto(val email: String, val password: String)
data class TokenResponseDto(val token: String)
