package com.gyub.kurly.model.base

/**
 *
 *
 * @author   Gyub
 * @created  2024/03/31
 */
sealed class UiState<out T> {

    data class Success<out T>(
        val value: T
    ) : UiState<T>()

    data object Loading : UiState<Nothing>()
    data object Error : UiState<Nothing>()
}