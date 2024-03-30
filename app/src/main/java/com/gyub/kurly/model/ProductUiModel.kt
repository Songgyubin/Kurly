package com.gyub.kurly.model

/**
 * Product UI Model
 *
 * @author   Gyub
 * @created  2024/03/30
 */
data class ProductUiModel(
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val originalPrice: Int = 0,
    val discountedPrice: Int = 0,
    val isSoldOut: Boolean = false
)