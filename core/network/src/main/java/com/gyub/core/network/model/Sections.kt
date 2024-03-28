package com.gyub.core.network.model

import com.gyub.core.network.model.base.BaseResponse

/**
 * Sections 응답 모델
 *
 * @author   Gyub
 * @created  2024/03/28
 */
data class Sections(
    val data: List<Item>?
) : BaseResponse()

data class Item(
    val title: String?,
    val id: Int?,
    val type: String?,
    val url: String?
)