package com.dominikdev.tinker.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Service

@Service
class UserIdentityService(
    private val repository: UserIdentityRepository
) : UserDetailsManager {
    override fun loadUserByUsername(username: String?): UserDetails? {
        if (username.isNullOrEmpty()) {
            return null
        }
        return repository.findByEmail(username)
    }

    override fun createUser(user: UserDetails?) {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: UserDetails?) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(username: String?) {
        TODO("Not yet implemented")
    }

    override fun changePassword(oldPassword: String?, newPassword: String?) {
        TODO("Not yet implemented")
    }

    override fun userExists(username: String?): Boolean {
        TODO("Not yet implemented")
    }
}