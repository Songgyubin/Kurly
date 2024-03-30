package com.gyub.core.data.model

import com.gyub.core.domain.model.SectionEntity
import com.gyub.core.network.model.Section

/**
 * [Section] to [SectionEntity]
 *
 * @author   Gyub
 * @created  2024/03/29
 */
fun Section.toEntity(): SectionEntity {
    return SectionEntity(
        title = title,
        id = id,
        type = type,
        url = url
    )
}