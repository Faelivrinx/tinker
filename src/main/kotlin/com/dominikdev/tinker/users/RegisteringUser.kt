package com.dominikdev.tinker.users

import com.dominikdev.tinker.security.SecurityFacade
import com.dominikdev.tinker.shared.OperationResult
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class RegisteringUser(
    private val securityFacade: SecurityFacade,
    private val expertRepository: ExpertsRepository
) {
    @Transactional
    fun registerExpert(dto: CreateExpertDto) : OperationResult<Unit> {
        val result = securityFacade.createUserIdentityFrom(dto.email, dto.password, listOf("ROLE_EXPERT"))

        return when(result) {
            is OperationResult.Success -> {
                expertRepository.save(Expert(
                    identityEmail = result.data.email
                ))

                OperationResult.Success(Unit)
            }
            is OperationResult.Failure -> {
                OperationResult.Failure(result.error, result.exception)
            }
        }
    }

}