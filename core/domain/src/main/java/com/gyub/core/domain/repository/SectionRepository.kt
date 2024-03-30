package com.gyub.core.domain.repository

import com.gyub.core.domain.model.ProductEntity
import com.gyub.core.domain.model.SectionsEntity
import com.gyub.core.domain.model.base.Resource

/**
 * Section Repository
 *
 * @author   Gyub
 * @created  2024/03/29
 */
interface SectionRepository {
    /**
     * 각 섹션 가져오기
     */
    suspend fun getSections(page: Int): Resource<SectionsEntity>

    /**
     * Section 별 상품 리스트 가져오기
     */
    suspend fun getSectionProducts(sectionId: Int): Resource<List<ProductEntity>>
}