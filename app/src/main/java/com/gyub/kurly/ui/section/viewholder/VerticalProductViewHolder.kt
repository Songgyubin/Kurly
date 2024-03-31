package com.gyub.kurly.ui.section.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.gyub.kurly.databinding.AdapterVerticalProductBinding
import com.gyub.kurly.model.section.ProductUiModel
import com.gyub.kurly.ui.section.adapter.VerticalProductAdapter
import com.gyub.kurly.ui.section.viewbinder.SectionViewBinder
import com.gyub.kurly.util.extension.executeAfter

/**
 * Vertical 타입 섹션의 상품 ViewHolder
 *
 * @author   Gyub
 * @created  2024/03/31
 */
class VerticalProductViewHolder(
    private val binding: AdapterVerticalProductBinding,
    private val viewBinder: SectionViewBinder
) : RecyclerView.ViewHolder(binding.root) {
    private val adapter by lazy { bindingAdapter as VerticalProductAdapter }

    init {
        setUpEvent()
    }

    /**
     * 클릭 이벤트 세팅
     */
    private fun setUpEvent() {
        setWishClick()
    }

    /**
     * 찜 클릭 이벤트 세팅
     */
    private fun setWishClick() {
        binding.imgWish.setOnClickListener {
            adapter.currentList[bindingAdapterPosition]
                .run {
                    onWishClicked(id, isWish)
                }
        }
    }

    /**
     * View Bind
     *
     * @param item [ProductUiModel]
     */
    fun bind(item: ProductUiModel) {
        binding.executeAfter {
            viewBinder.run {
                setProductImage(imgProduct, item.image)
                setProductTitle(textProductTitle, item.name)
                setDiscountPercent(textDiscountPercent, item.originalPrice, item.discountedPrice)
                setSalePrice(textSalePrice, item.originalPrice, item.discountedPrice)
                setOriginalPrice(textOriginalPrice, item.originalPrice, item.discountedPrice)
                setWishIcon(imgWish, item.isWish)
            }
        }
    }
}