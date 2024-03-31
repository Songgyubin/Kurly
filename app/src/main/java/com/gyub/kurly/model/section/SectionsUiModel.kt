package com.gyub.kurly.model.section

import com.gyub.core.common.util.extension.orDefault
import com.gyub.core.domain.model.SectionEntity
import com.gyub.core.domain.model.SectionsEntity

/**
 * Sections UI Model
 *
 * @author   Gyub
 * @created  2024/03/30
 */
data class SectionsUiModel(
    val sections: List<SectionUiModel> = listOf(),
    val nextPage: Int = 0
)

data class SectionUiModel(
    val title: String = "",
    val id: Int = 0,
    val type: String = "",
    val url: String = ""
)

/**
 * Mapper
 * [SectionsEntity] to [SectionsUiModel]
 */
fun SectionsEntity?.toUiModel(): SectionsUiModel {
    return SectionsUiModel(
        sections = this?.sections?.map { it.toUiModel() }.orEmpty(),
        nextPage = this?.nextPage.orDefault()
    )
}

/**
 * Mapper
 * [SectionEntity] to [SectionUiModel]
 */
fun SectionEntity?.toUiModel(): SectionUiModel {
    return SectionUiModel(
        title = this?.title.orEmpty(),
        id = this?.id.orDefault(),
        type = this?.type.orEmpty(),
        url = this?.url.orEmpty()
    )
}