package com.gyub.core.network.model.base

/**
 * Base 응답 모델
 *
 * @author   Gyub
 * @created  2024/03/28
 */
open class BaseResponse(
    val success: Boolean? = false,
    val message: String? = null
)