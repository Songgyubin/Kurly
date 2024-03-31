package com.gyub.kurly.model.section

/**
 * Section 타입 구분 Sealed Class
 *
 * @author   Gyub
 * @created  2024/03/31
 */
sealed class CombinedSectionTypeModel {
    data class VerticalSection(val data: CombinedSectionUiModel) : CombinedSectionTypeModel()
    data class HorizontalSection(val data: CombinedSectionUiModel) : CombinedSectionTypeModel()
    data class GridSection(val data: CombinedSectionUiModel) : CombinedSectionTypeModel()
}

fun CombinedSectionTypeModel.getData(): CombinedSectionUiModel {
    return when (this) {
        is CombinedSectionTypeModel.VerticalSection -> data
        is CombinedSectionTypeModel.HorizontalSection -> data
        is CombinedSectionTypeModel.GridSection -> data
    }
}