package com.dominikdev.tinker.users.registration

import com.dominikdev.tinker.shared.OperationResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/register")
class RegistrationUserEndpoint(
    private val user: RegisteringExpert
) {

    @PostMapping("/expert")
    fun registerExpert(@RequestBody dto: CreateExpertDto): ResponseEntity<Any> {
        return when (val result = user.registerExpert(dto)) {
            is OperationResult.Success -> ResponseEntity.ok(result.data)
            is OperationResult.Failure -> ResponseEntity.badRequest().body(result.error)
        }
    }

    @PostMapping("/confirm")
    fun confirmingExpert(@RequestBody dto: ConfirmExpertDto): ResponseEntity<Any> {
        return when (val result = user.confirming(dto.id, dto.code, dto.userId)) {
            is OperationResult.Success -> ResponseEntity.ok(result.data)
            is OperationResult.Failure -> ResponseEntity.badRequest().body(result.error)
        }
    }
}

data class CreateExpertDto(val email: String, val password: String)
data class ConfirmExpertDto(val id: UUID, val code: String, val userId: Long)