package com.gyub.core.network.model

/**
 * 섹션별 상품 응답 모델
 *
 * @author   Gyub
 * @created  2024/03/28
 */
data class SectionProducts(
    val data: List<Product>?
)

data class Product(
    val id: Int?,
    val name: String?,
    val image: String?,
    val originalPrice: Int?,
    val discountedPrice: Int?,
    val isSoldOut: Boolean?
)