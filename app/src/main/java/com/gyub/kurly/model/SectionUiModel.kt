package com.gyub.kurly.model

import com.gyub.common.util.extension.orDefault
import com.gyub.core.domain.model.SectionEntity

/**
 * Section UI Model
 *
 * @author   Gyub
 * @created  2024/03/30
 */
data class SectionUiModel(
    val title: String = "",
    val id: Int = 0,
    val type: String = "",
    val url: String = ""
)

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