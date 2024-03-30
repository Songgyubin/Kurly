package com.gyub.core.domain.fake

import com.gyub.core.domain.model.ProductEntity
import com.gyub.core.domain.model.SectionEntity
import com.gyub.core.domain.model.SectionsEntity
import com.gyub.core.domain.repository.SectionRepository

/**
 * Fake Section Repository 구현체
 *
 * @author   Gyub
 * @created  2024/03/29
 */
class FakeSectionRepository : SectionRepository {

    /**
     * 각 섹션 가져오기
     */
    override suspend fun getSections(page: Int): SectionsEntity {
        return SectionsEntity(
            sections = listOf(
                SectionEntity(
                    title = "함께하면 더 좋은 상품",
                    id = 1,
                    type = "grid",
                    url = "section_products_1"
                )
            ),
            nextPage = 2
        )
    }

    /**
     * Section 별 상품 리스트 가져오기
     */
    override suspend fun getSectionProducts(sectionId: Int): List<ProductEntity> {
        return listOf(
            ProductEntity(
                id = 5063110,
                name = "[연세우유 x 마켓컬리] 전용목장우유 900mL",
                image = "https://img-cf.kurly.com/shop/data/goods/1637154205701l0.jpg",
                originalPrice = 2070,
                discountedPrice = 1800,
                isSoldOut = false
            )
        )
    }
}