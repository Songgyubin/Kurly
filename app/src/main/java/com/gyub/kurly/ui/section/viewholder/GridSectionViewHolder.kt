package com.gyub.kurly.ui.section.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gyub.kurly.databinding.AdapterGridSectionBinding
import com.gyub.kurly.model.section.CombinedSectionUiModel
import com.gyub.kurly.model.section.ProductUiModel
import com.gyub.kurly.ui.section.adapter.GridProductAdapter
import com.gyub.kurly.ui.section.viewbinder.SectionViewBinder
import com.gyub.kurly.util.extension.executeAfter

/**
 * Grid 타입 섹션 뷰홀더
 *
 * @author   Gyub
 * @created  2024/03/31
 */
class GridSectionViewHolder(
    private val binding: AdapterGridSectionBinding,
    private val viewBinder: SectionViewBinder
) : ViewHolder(binding.root) {

    init {
        setUpLayout()
    }

    /**
     * 레이아웃 세팅
     */
    private fun setUpLayout() {
        setUpGridProductRecyclerView(binding.recyclerGridProduct)
    }

    /**
     * Grid 타입 섹션 내 상품 RecyclerView 초기 세팅
     *
     * @param view
     */
    private fun setUpGridProductRecyclerView(view: RecyclerView) {
        view.run {
            adapter = GridProductAdapter(viewBinder)

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
                setProductList(recyclerGridProduct, products)
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
            (adapter as GridProductAdapter).submitList(products)
        }
    }
}
