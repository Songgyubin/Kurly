package com.gyub.kurly.model

import com.gyub.core.domain.model.CombinedSectionEntity

/**
 * 섹션 + 상품 UI Model
 *
 * @author   Gyub
 * @created  2024/03/30
 */
data class CombinedSectionUiModel(
    val section: SectionUiModel = SectionUiModel(),
    val products: List<ProductUiModel> = emptyList()
)

/**
 * Mapper
 * [CombinedSectionEntity] to [CombinedSectionUiModel]
 */
fun CombinedSectionEntity.toUiModel(): CombinedSectionUiModel {
    return CombinedSectionUiModel(
        section = section.toUiModel(),
        products = products?.map { it.toUiModel() }.orEmpty()
    )
}