package com.gyub.kurly.model

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