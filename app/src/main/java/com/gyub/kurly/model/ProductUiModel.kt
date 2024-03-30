package com.gyub.kurly.model

import com.gyub.common.util.extension.orDefault
import com.gyub.core.domain.model.ProductEntity

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

/**
 * Mapper
 * [ProductEntity] to [ProductUiModel]
 */
fun ProductEntity?.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = this?.id.orDefault(),
        name = this?.name.orEmpty(),
        image = this?.image.orEmpty(),
        originalPrice = this?.originalPrice.orDefault(),
        discountedPrice = this?.discountedPrice.orDefault(),
        isSoldOut = this?.isSoldOut.orDefault(false)
    )
}