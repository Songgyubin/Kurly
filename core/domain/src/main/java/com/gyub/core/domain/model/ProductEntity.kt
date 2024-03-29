package com.gyub.core.domain.model

/**
 * 상품 Entity
 *
 * @author   Gyub
 * @created  2024/03/29
 */
data class ProductEntity(
    val id: Int?,
    val name: String?,
    val image: String?,
    val originalPrice: Int?,
    val discountedPrice: Int?,
    val isSoldOut: Boolean?
)