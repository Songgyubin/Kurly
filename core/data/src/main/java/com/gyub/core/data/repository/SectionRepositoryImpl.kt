package com.gyub.core.data.repository

import com.gyub.core.domain.model.ProductEntity
import com.gyub.core.domain.model.SectionEntity
import com.gyub.core.domain.repository.SectionRepository
import com.gyub.core.data.datasource.SectionsDataSource
import com.gyub.core.data.model.toEntity
import javax.inject.Inject

/**
 * Section Repository 구현체
 *
 * @author   Gyub
 * @created  2024/03/29
 */
class SectionRepositoryImpl @Inject constructor(
    private val sectionsDataSource: SectionsDataSource
) : SectionRepository {

    /**
     * 각 섹션 가져오기
     */
    override suspend fun getSections(page: Int): List<SectionEntity> {
        return sectionsDataSource.getSections(page).map {
            it.toEntity()
        }
    }

    /**
     * Section 별 상품 리스트 가져오기
     */
    override suspend fun getSectionProducts(sectionId: Int): List<ProductEntity> {
        return sectionsDataSource.getSectionProducts(sectionId).map {
            it.toEntity()
        }
    }
}