package com.gyub.kurly.ui.section.viewholder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyub.kurly.R
import com.gyub.kurly.constant.enums.SectionViewType
import com.gyub.kurly.constant.enums.SectionViewType.GRID_SECTION
import com.gyub.kurly.constant.enums.SectionViewType.HORIZONTAL_SECTION
import com.gyub.kurly.constant.enums.SectionViewType.VERTICAL_SECTION
import com.gyub.kurly.ui.section.viewbinder.SectionViewBinder
import com.gyub.kurly.util.extension.viewBind

/**
 * 섹션 ViewHolder Factory
 *
 * @author   Gyub
 * @created  2024/04/01
 */
class SectionViewHolderFactory(
    private val viewBinder: SectionViewBinder
) {

    /**
     * view type에 맞는 ViewHolder 반환
     *
     * @param parent
     * @param viewType
     * @return view type에 맞는 ViewHolder
     */
    fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val sectionViewType = SectionViewType.getViewTypeForOrdinal(viewType)

        return when (sectionViewType) {
            VERTICAL_SECTION -> createVerticalSectionViewHolder(parent)
            HORIZONTAL_SECTION -> createHorizontalSectionViewHolder(parent)
            GRID_SECTION -> createGridSectionViewHolder(parent)
        }
    }

    /**
     * Vertical 타입 섹션 ViewHolder 생성
     *
     * @param parent
     * @return Vertical 타입 섹션 ViewHolder
     */
    private fun createVerticalSectionViewHolder(
        parent: ViewGroup,
    ): VerticalSectionViewHolder {
        return VerticalSectionViewHolder(
            viewBind(parent, R.layout.adapter_vertical_section),
            viewBinder
        )
    }

    /**
     * Horizontal 타입 섹션 ViewHolder 생성
     *
     * @param parent
     * @return Horizontal 타입 섹션 ViewHolder
     */
    private fun createHorizontalSectionViewHolder(
        parent: ViewGroup
    ): HorizontalSectionViewHolder {
        return HorizontalSectionViewHolder(
            viewBind(parent, R.layout.adapter_horizontal_section),
            viewBinder
        )
    }

    /**
     * Grid 타입 섹션 ViewHolder 생성
     *
     * @param parent
     * @return Grid 타입 섹션 ViewHolder
     */
    private fun createGridSectionViewHolder(
        parent: ViewGroup
    ): GridSectionViewHolder {
        return GridSectionViewHolder(
            viewBind(parent, R.layout.adapter_grid_section),
            viewBinder
        )
    }
}