package com.gyub.kurly.ui.section.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gyub.kurly.R
import com.gyub.kurly.model.section.ProductUiModel
import com.gyub.kurly.ui.section.viewbinder.SectionViewBinder
import com.gyub.kurly.ui.section.viewholder.GridProductViewHolder
import com.gyub.kurly.util.extension.viewBind

/**
 * Grid 타입 섹션의 상품 Adapter
 *
 * @author   Gyub
 * @created  2024/04/01
 */
class GridProductAdapter(
    private val viewBinder: SectionViewBinder
) : ListAdapter<ProductUiModel, GridProductViewHolder>(GridProductDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridProductViewHolder {
        return GridProductViewHolder(
            viewBind(parent, R.layout.adapter_grid_product),
            viewBinder
        )
    }

    override fun onBindViewHolder(holder: GridProductViewHolder, position: Int) {
        val item = currentList.getOrElse(position) { return }
        holder.bind(item)
    }
}

class GridProductDiffUtilCallback : DiffUtil.ItemCallback<ProductUiModel>() {
    override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean =
        oldItem == newItem
}