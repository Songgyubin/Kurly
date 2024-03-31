package com.gyub.kurly.constant.enums

/**
 * 섹션 뷰 타입
 *
 * @author   Gyub
 * @created  2024/04/01
 */
enum class SectionViewType(val type: String) {
    VERTICAL_SECTION("vertical"),
    HORIZONTAL_SECTION("horizontal"),
    GRID_SECTION("grid");

    companion object {
        /**
         * type으로 SectionViewType index 반환
         *
         * @param type
         * @return
         */
        fun getOrdinalForType(type: String): Int {
            return when (type) {
                VERTICAL_SECTION.type -> VERTICAL_SECTION.ordinal
                HORIZONTAL_SECTION.type -> HORIZONTAL_SECTION.ordinal
                GRID_SECTION.type -> GRID_SECTION.ordinal
                else -> -1
            }
        }

        /**
         * ordinal 값으로 SectionViewType 반환
         *
         * @param ordinal
         * @return
         */
        fun getViewTypeForOrdinal(ordinal: Int): SectionViewType {
            val sortedByOrdinal = SectionViewType.entries.sortedBy { it.ordinal }

            return sortedByOrdinal[ordinal]
        }
    }
}