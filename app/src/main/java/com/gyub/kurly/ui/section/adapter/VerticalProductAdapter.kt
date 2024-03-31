package com.gyub.kurly.ui.section.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gyub.kurly.R
import com.gyub.kurly.model.section.ProductUiModel
import com.gyub.kurly.ui.section.viewbinder.SectionViewBinder
import com.gyub.kurly.ui.section.viewholder.VerticalProductViewHolder
import com.gyub.kurly.util.extension.viewBind

/**
 * Vertical 타입 섹션의 상품 ViewHolder
 *
 * @author   Gyub
 * @created  2024/04/01
 */
class VerticalProductAdapter(
    private val viewBinder: SectionViewBinder
) : ListAdapter<ProductUiModel, VerticalProductViewHolder>(VerticalProductDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalProductViewHolder {
        return VerticalProductViewHolder(
            viewBind(parent, R.layout.adapter_vertical_product),
            viewBinder
        )
    }

    override fun onBindViewHolder(holder: VerticalProductViewHolder, position: Int) {
        val item = currentList.getOrElse(position) { return }
        holder.bind(item)
    }
}

class VerticalProductDiffUtilCallback : DiffUtil.ItemCallback<ProductUiModel>() {
    override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean =
        oldItem == newItem
}