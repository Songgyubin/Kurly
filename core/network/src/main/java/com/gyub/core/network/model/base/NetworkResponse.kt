package com.gyub.core.network.model.base

/**
 * Base Network 응답 모델
 *
 * @author   Gyub
 * @created  2024/03/29
 */
data class NetworkResponse<T>(
    val data: T
)
