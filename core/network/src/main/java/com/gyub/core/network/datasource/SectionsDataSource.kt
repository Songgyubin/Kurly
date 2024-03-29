package com.gyub.core.network.datasource

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
}