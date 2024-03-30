package com.gyub.core.domain.model

/**
 * Sections Entity
 *
 * @author   Gyub
 * @created  2024/03/29
 */
data class SectionsEntity(
    val sections: List<SectionEntity>?,
    val nextPage: Int?
)

data class SectionEntity(
    val title: String?,
    val id: Int?,
    val type: String?,
    val url: String?
)