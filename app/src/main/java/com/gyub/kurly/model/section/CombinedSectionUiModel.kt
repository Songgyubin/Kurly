package com.gyub.kurly.model.section

import com.gyub.core.domain.model.CombinedSectionEntity
import com.gyub.kurly.constant.enums.SectionViewType

/**
 * 섹션 + 상품 UI Model
 *
 * @author   Gyub
 * @created  2024/03/30
 */
data class CombinedSectionUiModel(
    val section: SectionUiModel = SectionUiModel(),
    val products: List<ProductUiModel> = emptyList(),
)


/**
 * Mapper
 * [CombinedSectionEntity] to [CombinedSectionUiModel]
 */
fun CombinedSectionEntity.toUiModel(): CombinedSectionUiModel {
    return CombinedSectionUiModel(
        section.toUiModel(),
        products?.map { it.toUiModel() }.orEmpty()
    )
}

///**
// * Mapper
// * [CombinedSectionEntity] to []
// */
//fun Pair<List<CombinedSectionEntity>, Int>.toUiModel(): CombinedSectionsUiModel {
//    return CombinedSectionsUiModel(
//        combinedSections = this.first.map { entity ->
//            CombinedSectionUiModel(
//                entity.section.toUiModel(),
//                entity.products?.map { it.toUiModel() }.orEmpty()
//            )
//        }, nextPage = this.second.orDefault()
//    )
//}


/**
 * Mapper
 * [CombinedSectionUiModel] to [CombinedSectionTypeModel]
 *
 * @return
 */
fun CombinedSectionUiModel.toTypeModel(): CombinedSectionTypeModel {
    val type = section.type
    val ordinal = SectionViewType.getOrdinalForType(type)
    val viewType = SectionViewType.getViewTypeForOrdinal(ordinal)

    return when (viewType) {
        SectionViewType.VERTICAL_SECTION -> CombinedSectionTypeModel.VerticalSection(this)
        SectionViewType.HORIZONTAL_SECTION -> CombinedSectionTypeModel.HorizontalSection(this)
        SectionViewType.GRID_SECTION -> CombinedSectionTypeModel.GridSection(this)
    }
}