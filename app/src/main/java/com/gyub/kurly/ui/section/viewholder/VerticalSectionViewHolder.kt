package com.gyub.kurly.ui.section.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.gyub.kurly.databinding.AdapterVerticalSectionBinding
import com.gyub.kurly.model.section.CombinedSectionUiModel
import com.gyub.kurly.model.section.ProductUiModel
import com.gyub.kurly.ui.section.adapter.VerticalProductAdapter
import com.gyub.kurly.ui.section.viewbinder.SectionViewBinder
import com.gyub.kurly.util.extension.executeAfter

/**
 * Vertical 타입 섹션 뷰홀더
 *
 * @author   Gyub
 * @created  2024/03/31
 */
class VerticalSectionViewHolder(
    private val binding: AdapterVerticalSectionBinding,
    private val viewBinder: SectionViewBinder
) : RecyclerView.ViewHolder(binding.root) {

    init {
        setUpLayout()
    }

    /**
     * 레이아웃 세팅
     */
    private fun setUpLayout() {
        setUpVerticalProductRecyclerView(binding.recyclerVerticalProduct)
    }

    /**
     * Vertical 타입 섹션 내 상품 RecyclerView 초기 세팅
     *
     * @param view
     */
    private fun setUpVerticalProductRecyclerView(view: RecyclerView) {
        view.run {
            adapter = VerticalProductAdapter(viewBinder)

            itemAnimator = null
            setHasFixedSize(true)
        }
    }

    /**
     * View Bind
     *
     * @param combinedSection [CombinedSectionUiModel]
     */
    fun bind(combinedSection: CombinedSectionUiModel) {
        val section = combinedSection.section
        val products = combinedSection.products

        binding.executeAfter {

            viewBinder.run {
                setSectionTitle(textSectionTitle, section.title)
                setProductList(recyclerVerticalProduct, products)
            }
        }
    }

    /**
     * 상품 리스트 세팅
     *
     * @param view
     * @param products 상품 리스트
     */
    private fun setProductList(view: RecyclerView, products: List<ProductUiModel>) {
        view.run {
            (adapter as VerticalProductAdapter).submitList(products)
        }
    }
}