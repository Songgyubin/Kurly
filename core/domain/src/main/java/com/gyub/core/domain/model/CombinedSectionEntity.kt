package com.gyub.core.domain.model

/**
 * 섹션과 상품 리스트가 결합된 Entity
 *
 * @author   Gyub
 * @created  2024/04/02
 */
data class CombinedSectionEntity(
    val section: SectionEntity?,
    val products: List<ProductEntity>?,
)