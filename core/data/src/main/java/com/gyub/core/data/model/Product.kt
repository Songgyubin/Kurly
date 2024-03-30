package com.gyub.core.data.model

import com.gyub.core.domain.model.ProductEntity
import com.gyub.core.network.model.Product

/**
 * [Product] to [ProductEntity]
 *
 * @author   Gyub
 * @created  2024/03/29
 */
fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        image = image,
        originalPrice = originalPrice,
        discountedPrice = discountedPrice,
        isSoldOut = isSoldOut
    )
}