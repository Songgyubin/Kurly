package com.gyub.core.domain.repository

import com.gyub.core.domain.model.SectionEntity

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
    suspend fun getSections(page: Int): List<SectionEntity>
}