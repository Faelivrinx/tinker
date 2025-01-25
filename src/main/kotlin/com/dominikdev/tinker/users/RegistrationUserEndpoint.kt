package com.dominikdev.tinker.users

import com.dominikdev.tinker.shared.OperationResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/register")
class RegistrationUserEndpoint(
    private val user: RegisteringUser
) {

    @PostMapping("/expert")
    fun registerExpert(@RequestBody dto: CreateExpertDto): ResponseEntity<Any> {
        return when (val result = user.registerExpert(dto)) {
            is OperationResult.Success -> ResponseEntity.ok(result.data)
            is OperationResult.Failure -> ResponseEntity.badRequest().body(result.error)
        }
    }
}

data class CreateExpertDto(val email: String, val password: String)