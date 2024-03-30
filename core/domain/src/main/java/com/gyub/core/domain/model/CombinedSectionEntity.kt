package com.gyub.core.domain.model

/**
 * 섹션 데이터와 섹션 별 상품 리스트가 결합된 Entity
 *
 * @author   Gyub
 * @created  2024/03/29
 */
data class CombinedSectionEntity(
    val section: SectionEntity?,
    val products: List<ProductEntity>?
)
