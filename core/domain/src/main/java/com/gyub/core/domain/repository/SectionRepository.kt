package com.gyub.core.domain.repository

import com.gyub.core.domain.model.SectionEntity

/**
 * Section Repository
 *
 * @author   Gyub
 * @created  2024/03/29
 */
interface SectionRepository {
    fun getSections(page: Int): List<SectionEntity>
}