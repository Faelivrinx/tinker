package com.dominikdev.tinker.shared

sealed class OperationResult<out T> {
    data class Success<T>(val data: T) : OperationResult<T>()
    data class Failure(val error: String, val exception: Throwable? = null) : OperationResult<Nothing>()

    fun isSuccess(): Boolean = this is Success
    fun isFailure(): Boolean = this is Failure
}

inline fun <T, R> OperationResult<T>.map(transform: (T) -> R): OperationResult<R> {
    return when (this) {
        is OperationResult.Success -> OperationResult.Success(transform(data))
        is OperationResult.Failure -> this
    }
}