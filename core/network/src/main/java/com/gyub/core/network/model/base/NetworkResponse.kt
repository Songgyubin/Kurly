package com.gyub.core.network.model.base

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Base Network 응답 모델
 *
 * @author   Gyub
 * @created  2024/03/29
 */
@Keep
data class NetworkResponse<T>(
    val data: T,
    val paging: Paging? = null
)

/**
 * Paging 응답
 * 다음 페이지가 없을 시 null
 */
@Keep
data class Paging(
    @SerializedName("next_page")
    val nextPage: Int
)
