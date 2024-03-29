package com.gyub.core.network.datasource

import com.gyub.core.network.model.Product
import com.gyub.core.network.model.Section
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
    suspend fun getSections(page: Int): List<Section> {
        return sectionApiService.getSections(page).data
    }

    /**
     * Section 별 상품 리스트 가져오기
     */
    suspend fun getSectionProducts(sectionId: Int): List<Product> {
        return sectionApiService.getSectionProducts(sectionId).data
    }
}