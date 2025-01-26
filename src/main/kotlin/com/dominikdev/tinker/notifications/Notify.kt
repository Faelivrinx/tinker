package com.dominikdev.tinker.notifications

import com.dominikdev.tinker.shared.OperationResult

interface Notify<T> {
    fun sendNotification(payload: T) : OperationResult<Unit>
}