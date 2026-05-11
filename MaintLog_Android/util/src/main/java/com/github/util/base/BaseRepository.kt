package com.github.util.base

import retrofit2.Response

open class BaseRepository {
    val TAG = javaClass.simpleName

    /*
    * service의 suspend 함수를 받아 RepoResult로 반환
    * */
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): RepositoryResult<T> {
        return try {
            call.invoke().let {
                if (it.isSuccessful) {
                    RepositoryResult.Success(it.body())
                } else {
                    val error = when (it.code()) {
                        403 -> "Authentication failed"
                        else -> "error"
                    }
                    RepositoryResult.Error(it.errorBody()?.string() ?: error)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            RepositoryResult.Error(e.message ?: "Internet error runs")
        }
    }
}