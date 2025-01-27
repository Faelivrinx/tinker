package com.dominikdev.tinker.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserIdentityService(
    private val repository: UserIdentityRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        if (username.isNullOrEmpty()) {
            return null
        }
        return repository.findByEmail(username)
    }
}