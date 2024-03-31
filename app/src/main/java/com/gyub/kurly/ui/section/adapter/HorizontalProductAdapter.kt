package com.gyub.kurly.ui.section.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gyub.kurly.R
import com.gyub.kurly.model.section.ProductUiModel
import com.gyub.kurly.ui.section.viewbinder.SectionViewBinder
import com.gyub.kurly.ui.section.viewholder.HorizontalProductViewHolder
import com.gyub.kurly.util.extension.viewBind

/**
 * Horizontal 타입 섹션의 상품 ViewHolder
 *
 * @author   Gyub
 * @created  2024/04/01
 */
class HorizontalProductAdapter(
    private val viewBinder: SectionViewBinder
) : ListAdapter<ProductUiModel, HorizontalProductViewHolder>(HorizontalProductDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalProductViewHolder {
        return HorizontalProductViewHolder(
            viewBind(parent, R.layout.adapter_horizontal_product),
            viewBinder
        )
    }

    override fun onBindViewHolder(holder: HorizontalProductViewHolder, position: Int) {
        val item = currentList.getOrElse(position) { return }
        holder.bind(item)
    }
}

class HorizontalProductDiffUtilCallback : DiffUtil.ItemCallback<ProductUiModel>() {
    override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean =
        oldItem == newItem
}