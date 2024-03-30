package com.gyub.core.data.model

import com.gyub.core.domain.model.SectionEntity
import com.gyub.core.domain.model.SectionsEntity
import com.gyub.core.network.model.Section
import com.gyub.core.network.model.Sections

/**
 * [Sections] to [SectionsEntity]
 *
 * @author   Gyub
 * @created  2024/03/29
 */
fun Sections.toEntity(): SectionsEntity {
    return SectionsEntity(
        sections = sections?.map { it.toEntity() },
        nextPage = nextPage
    )
}

fun Section.toEntity(): SectionEntity {
    return SectionEntity(
        title = title,
        id = id,
        type = type,
        url = url
    )
}