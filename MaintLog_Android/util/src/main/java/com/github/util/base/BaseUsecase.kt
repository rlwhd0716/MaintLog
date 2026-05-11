package com.github.util.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

interface BaseUseCase<IN, OUT : Any> {
    fun execute(input: IN): Flow<UsecaseResult<OUT>>
}

fun <T : Any> setChannelFlow(block: suspend () -> RepositoryResult<T>) = channelFlow {
    trySend(UsecaseResult.Loading)
    block.invoke().let {
        when (it) {
            is RepositoryResult.Success -> trySend(UsecaseResult.Success(it.data))
            is RepositoryResult.Error -> trySend(UsecaseResult.Error(it.exception))
        }
    }
    trySend(UsecaseResult.Complete)
}
