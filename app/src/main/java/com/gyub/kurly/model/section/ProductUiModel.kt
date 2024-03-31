package com.gyub.kurly.model.section

import com.gyub.common.util.extension.orDefault
import com.gyub.core.common.util.extension.orDefault
import com.gyub.core.domain.model.ProductEntity
import com.gyub.kurly.ui.section.event.WishEvent

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
    val isSoldOut: Boolean = false,
    val isWish: Boolean = false
) : WishEvent {
    override var onWishClicked: (Int, Boolean) -> Unit = { _, _ -> }
}

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