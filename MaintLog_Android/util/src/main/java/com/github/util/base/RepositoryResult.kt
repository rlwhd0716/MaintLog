package com.github.util.base

sealed class RepositoryResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : RepositoryResult<T>()
    data class Error(val exception: String) : RepositoryResult<Nothing>()
}