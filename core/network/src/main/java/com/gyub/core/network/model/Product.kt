package com.gyub.core.network.model

import androidx.annotation.Keep

/**
 * 섹션별 상품 응답 모델
 *
 * @author   Gyub
 * @created  2024/03/28
 */
@Keep
data class Product(
    val id: Int?,
    val name: String?,
    val image: String?,
    val originalPrice: Int?,
    val discountedPrice: Int?,
    val isSoldOut: Boolean?
)