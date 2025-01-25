package com.dominikdev.tinker.security

import org.springframework.data.jpa.repository.JpaRepository

interface UserIdentityRepository : JpaRepository<IdentityUserEntity, Long> {
    fun existsByEmail(email: String): Boolean
}