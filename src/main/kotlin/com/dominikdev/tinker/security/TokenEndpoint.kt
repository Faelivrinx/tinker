package com.dominikdev.tinker.security

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenEndpoint(val authService: AuthService) {

    @PostMapping("/token")
    fun token(@RequestBody request: TokenRequestDto): TokenResponseDto {
        return authService.authenticateWithToken(request)
    }

}

data class TokenRequestDto(val email: String, val password: String)
data class TokenResponseDto(val token: String)