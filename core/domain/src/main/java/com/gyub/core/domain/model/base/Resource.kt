package com.gyub.core.domain.model.base

/**
 * API 통신 결과(Response) Data를 적재하는 Wrapper Class
 *
 * @author   Gyub
 * @created  2024/03/30
 */
sealed class Resource<out T> {

    data class Success<out T>(
        val value: T,
    ) : Resource<T>()

    data object Failure : Resource<Nothing>()
    data object Error : Resource<Nothing>()
}

/**
 * Mapper
 *
 * @param T Input Resource
 * @param V Output Entity
 * @param callback Entity Converter High Order Function
 * @return Resource<V>
 */
inline fun <reified T, reified V> Resource<T>.convertIfSuccess(callback: (value: T) -> V): Resource<V> {
    return when (this) {
        is Resource.Error -> Resource.Error
        is Resource.Failure -> Resource.Failure
        is Resource.Success -> Resource.Success(callback(value))
    }
}