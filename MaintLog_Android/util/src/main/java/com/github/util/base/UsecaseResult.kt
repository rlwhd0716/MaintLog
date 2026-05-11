package com.github.util.base

sealed class UsecaseResult<out T : Any> {
    object Loading : UsecaseResult<Nothing>()

    data class Success<out T:Any>(val data:T?) : UsecaseResult<T>()

    data class Error(val exception:String) : UsecaseResult<Nothing>()

    object Complete: UsecaseResult<Nothing>()
}

fun <T : Any> UsecaseResult<T>.useCaseResult(loading: (() -> Unit)? = null, complete: (() -> Unit)? = null, error: ((String) -> Unit)? = null, success: (T?) -> Unit) {
    when (this) {
        is UsecaseResult.Loading -> loading?.run { invoke() }
        is UsecaseResult.Complete -> complete?.run { invoke() }
        is UsecaseResult.Success -> this.data.also(success)
        is UsecaseResult.Error -> error?.let { (it.invoke(this.exception)) }
        else -> {}
    }
}