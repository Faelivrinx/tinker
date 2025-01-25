package com.dominikdev.tinker.security

import com.dominikdev.tinker.shared.OperationResult
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SecurityFacade(
    private val identityRepository: UserIdentityRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun createUserIdentityFrom(email: String, password: String, roles: Collection<String>): OperationResult<CreationResult> {
        return try {
            if (identityRepository.existsByEmail(email)) {
                return OperationResult.Failure("User with email $email already exists")
            }

            identityRepository.save(IdentityUserEntity(
                username = email,
                email = email,
                password =  passwordEncoder.encode(password),
                enabled = true,
                authorities = roles,
                notBlocked = true,
            ))

            return OperationResult.Success(CreationResult(email))
        } catch (ex: Exception) {
            OperationResult.Failure("Failed to create user identity", ex)
        }
    }
}

data class CreationResult(val email: String)