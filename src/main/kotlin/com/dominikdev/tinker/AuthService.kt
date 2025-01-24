package com.dominikdev.tinker

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val tokenService: TokenService,
    private val userDetailsManager: UserDetailsManager
) {
    private val log = org.slf4j.LoggerFactory.getLogger(AuthService::class.java)

    fun authenticateWithToken(tokenRequest: TokenRequestDto): TokenResponseDto {
        val userDetails = try {
            userDetailsManager.loadUserByUsername(tokenRequest.email)
        } catch (e: UsernameNotFoundException) {
            log.error("User not found: {}", tokenRequest.email)
            throw RuntimeException("Invalid credentials") // Generic error for security
        }

        // SECURITY RISK: Using plain text password comparison.
        // In production, use a PasswordEncoder: passwordEncoder.matches(...)
        if (userDetails.password != tokenRequest.password) {
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
