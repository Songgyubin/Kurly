package com.gyub.kurly.ui.section.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gyub.kurly.constant.enums.SectionViewType
import com.gyub.kurly.model.section.CombinedSectionTypeModel
import com.gyub.kurly.model.section.CombinedSectionTypeModel.GridSection
import com.gyub.kurly.model.section.CombinedSectionTypeModel.HorizontalSection
import com.gyub.kurly.model.section.CombinedSectionTypeModel.VerticalSection
import com.gyub.kurly.ui.section.viewbinder.SectionViewBinder
import com.gyub.kurly.ui.section.viewholder.GridSectionViewHolder
import com.gyub.kurly.ui.section.viewholder.HorizontalSectionViewHolder
import com.gyub.kurly.ui.section.viewholder.SectionViewHolderFactory
import com.gyub.kurly.ui.section.viewholder.VerticalSectionViewHolder

/**
 * Section Adapter
 *
 * @author   Gyub
 * @created  2024/03/31
 */
class SectionsAdapter : ListAdapter<CombinedSectionTypeModel, ViewHolder>(SectionDiffUtilCallback()) {
    companion object {
        const val PREFETCH = 2
    }

    private val viewBinder: SectionViewBinder by lazy { SectionViewBinder() }
    private val viewHolderFactory = SectionViewHolderFactory(viewBinder)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return viewHolderFactory.createViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]

        return when (item) {
            is VerticalSection -> SectionViewType.VERTICAL_SECTION.ordinal
            is HorizontalSection -> SectionViewType.HORIZONTAL_SECTION.ordinal
            is GridSection -> SectionViewType.GRID_SECTION.ordinal
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList.getOrElse(position) { return }

        when (item) {
            is VerticalSection -> bindVerticalSectionViewHolder(holder, item)
            is HorizontalSection -> bindHorizontalSectionViewHolder(holder, item)
            is GridSection -> bindGridSectionViewHolder(holder, item)
        }
    }

    /**
     * [VerticalSection]을 뷰홀더에 bind
     *
     * @param holder [VerticalSectionViewHolder]
     * @param item [VerticalSection]
     */
    private fun bindVerticalSectionViewHolder(holder: ViewHolder, item: VerticalSection) {
        val verticalSectionViewHolder = holder as? VerticalSectionViewHolder ?: return

        verticalSectionViewHolder.bind(item.data)
    }

    /**
     * [HorizontalSection]을 뷰홀더에 bind
     *
     * @param holder [HorizontalSectionViewHolder]
     * @param item [HorizontalSection]
     */
    private fun bindHorizontalSectionViewHolder(holder: ViewHolder, item: HorizontalSection) {
        val horizontalSectionViewHolder = holder as? HorizontalSectionViewHolder ?: return

        horizontalSectionViewHolder.bind(item.data)
    }

    /**
     * [GridSection]을 뷰홀더에 bind
     *
     * @param holder [GridSectionViewHolder]
     * @param item [GridSection]
     */
    private fun bindGridSectionViewHolder(holder: ViewHolder, item: GridSection) {
        val gridSectionViewHolder = holder as? GridSectionViewHolder ?: return

        gridSectionViewHolder.bind(item.data)
    }

}

class SectionDiffUtilCallback : DiffUtil.ItemCallback<CombinedSectionTypeModel>() {
    override fun areItemsTheSame(oldItem: CombinedSectionTypeModel, newItem: CombinedSectionTypeModel): Boolean =
        when (oldItem) {
            is VerticalSection -> (newItem is VerticalSection) && oldItem.data.section.id == newItem.data.section.id
            is HorizontalSection -> (newItem is HorizontalSection) && oldItem.data.section.id == newItem.data.section.id
            is GridSection -> (newItem is GridSection) && oldItem.data.section.id == newItem.data.section.id
        }

    override fun areContentsTheSame(oldItem: CombinedSectionTypeModel, newItem: CombinedSectionTypeModel): Boolean =
        oldItem == newItem
}