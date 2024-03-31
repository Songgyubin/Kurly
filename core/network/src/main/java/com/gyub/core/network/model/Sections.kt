package com.gyub.core.network.model

import androidx.annotation.Keep

/**
 * Sections 응답 모델
 *
 * @author   Gyub
 * @created  2024/03/28
 */
@Keep
data class Sections(
    val sections: List<Section>?,
    val nextPage: Int?
)
@Keep
data class Section(
    val title: String?,
    val id: Int?,
    val type: String?,
    val url: String?,
)