package com.dominikdev.tinker.security

import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val tokenService: TokenService,
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) {
    private val log = LoggerFactory.getLogger(AuthService::class.java)

    fun authenticateWithToken(tokenRequest: TokenRequestDto): TokenResponseDto {
        val userDetails = try {
            userDetailsService.loadUserByUsername(tokenRequest.email)
        } catch (e: UsernameNotFoundException) {
            log.error("User not found: {}", tokenRequest.email)
            throw RuntimeException("Invalid credentials") // Generic error for security
        }

        if (!passwordEncoder.matches(tokenRequest.password, userDetails.password)) {
            log.error("Password mismatch for user: {}", tokenRequest.email)
            throw RuntimeException("Invalid credentials") // Generic error for security
        }

        val permissions = extractPermissions(userDetails)
        val jwtToken = tokenService.generateToken(userDetails.username, permissions)
        return TokenResponseDto(jwtToken)
    }

    private fun extractPermissions(userDetails: UserDetails): String {
        return userDetails.authorities.joinToString(" ") { it.authority }
    }
}
