package com.gyub.core.data.datasource

import com.gyub.core.network.model.Product
import com.gyub.core.network.model.Section
import com.gyub.core.network.model.Sections
import com.gyub.core.network.retrofit.SectionApiService
import javax.inject.Inject

/**
 * Section Data Source
 *
 * @author   Gyub
 * @created  2024/03/28
 */
class SectionsDataSource
@Inject constructor(
    private val sectionApiService: SectionApiService
) {
    /**
     * 각 섹션 가져오기
     */
    suspend fun getSections(page: Int): Sections {
        val result = sectionApiService.getSections(page)

        val sections = result.data
        val nextPage = result.paging?.nextPage

        return Sections(sections, nextPage)
    }

    /**
     * Section 별 상품 리스트 가져오기
     */
    suspend fun getSectionProducts(sectionId: Int): List<Product> {
        return sectionApiService.getSectionProducts(sectionId).data
    }
}