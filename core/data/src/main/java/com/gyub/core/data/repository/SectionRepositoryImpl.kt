package com.gyub.core.data.repository

import com.gyub.core.data.datasource.SectionsDataSource
import com.gyub.core.data.model.toEntity
import com.gyub.core.domain.model.ProductEntity
import com.gyub.core.domain.model.SectionsEntity
import com.gyub.core.domain.model.base.Resource
import com.gyub.core.domain.model.base.convertIfSuccess
import com.gyub.core.domain.repository.SectionRepository
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
    override suspend fun getSections(page: Int): Resource<SectionsEntity> {
        return sectionsDataSource.getSections(page).convertIfSuccess { sections ->
            sections.toEntity()
        }
    }

    /**
     * Section 별 상품 리스트 가져오기
     */
    override suspend fun getSectionProducts(sectionId: Int): Resource<List<ProductEntity>> {
        return sectionsDataSource.getSectionProducts(sectionId).convertIfSuccess { productList ->
            productList.map {
                it.toEntity()
            }
        }
    }
}