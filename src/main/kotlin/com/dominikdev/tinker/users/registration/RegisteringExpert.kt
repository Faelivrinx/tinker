package com.dominikdev.tinker.users.registration

import com.dominikdev.tinker.notifications.Notify
import com.dominikdev.tinker.security.SecurityFacade
import com.dominikdev.tinker.shared.OperationResult
import com.dominikdev.tinker.shared.map
import com.dominikdev.tinker.users.CodeEntity
import com.dominikdev.tinker.users.CodesRepositories
import com.dominikdev.tinker.users.Expert
import com.dominikdev.tinker.users.ExpertsRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegisteringExpert(
    private val securityFacade: SecurityFacade,
    private val expertRepository: ExpertsRepository,
    private val codeGenerator: CodeGenerator,
    private val codesRepository: CodesRepositories,
    private val notify: Notify<String>
) {
    private val logger = LoggerFactory.getLogger(RegisteringExpert::class.java)

    @Transactional
    fun registerExpert(dto: CreateExpertDto): OperationResult<Unit> {
        val result = securityFacade.createUserIdentityFrom(dto.email, dto.password, listOf("ROLE_EXPERT"))

        return when (result) {
            is OperationResult.Success -> {
                expertRepository.save(
                    Expert(
                        identityEmail = result.data.email
                    )
                )

                val generatedCode = codesRepository.save(
                    CodeEntity(
                        code = codeGenerator.generate()
                    )
                )
                return notify.sendNotification(generatedCode.id.toString())
            }

            is OperationResult.Failure -> {
                OperationResult.Failure(result.error, result.exception)
            }
        }
    }

    @Transactional
    fun confirming(id: UUID, code: String, userId: Long) : OperationResult<Long> = kotlin.runCatching {
        val codeEntity = codesRepository.findById(id).orElseThrow()
        if (Objects.equals(codeEntity.code, code)) {
            return securityFacade.enableAccount(userId)
        }
        return OperationResult.Failure("couldn't match code")
    }.getOrElse { ex ->
        logger.error("failed to match code", ex)
        return OperationResult.Failure("couldn't match code")
    }
}