package com.dominikdev.tinker.notifications

import com.dominikdev.tinker.shared.OperationResult
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ConsoleNotifier : Notify<String> {
    private val logger = LoggerFactory.getLogger(ConsoleNotifier::class.java)

    override fun sendNotification(payload: String): OperationResult<Unit> {
        logger.info("\nMessage: $payload\n")
        return OperationResult.Success(Unit)
    }
}