package com.gyub.kurly.ui.section

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyub.core.common.result.Result
import com.gyub.core.domain.usecase.GetCombinedSectionUseCase
import com.gyub.kurly.model.section.CombinedSectionTypeModel
import com.gyub.kurly.model.section.ProductUiModel
import com.gyub.kurly.model.section.getData
import com.gyub.kurly.model.section.toTypeModel
import com.gyub.kurly.model.section.toUiModel
import com.gyub.kurly.ui.section.event.applyOnWishClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Section ViewModel
 *
 * @author   Gyub
 * @created  2024/03/30
 */
@HiltViewModel
class SectionViewModel @Inject constructor(
    private val getCombinedSectionUseCase: GetCombinedSectionUseCase
) : ViewModel() {

    private val _sectionViewState: MutableStateFlow<SectionViewState> = MutableStateFlow(SectionViewState.Loading)
    val sectionViewState = _sectionViewState.asStateFlow()

    private var _currentSections: List<CombinedSectionTypeModel> = listOf()

    /**
     * 섹션 가져오기
     *
     * @param isFirstPage 첫 페이지 여부
     * @param nextPage 페이징을 위한 다음 페이지 번호
     */
    fun fetchSections(isFirstPage: Boolean = true, nextPage: Int = 1) {
        viewModelScope.launch {
            getCombinedSectionUseCase(nextPage)
                .map { result ->
                    when (result) {
                        is Result.Loading -> SectionViewState.Loading
                        is Result.Error -> SectionViewState.Error
                        is Result.Success -> {
                            val newSections = result.data.first
                                .map { it.toUiModel() }
                                .map {
                                    it.toTypeModel().apply {
                                        applyClicked()
                                    }
                                }

                            if (isFirstPage) {
                                SectionViewState.Success(newSections, result.data.second)
                            } else {
                                SectionViewState.Success(_currentSections + newSections, result.data.second)
                            }
                        }
                    }
                }
                .collect { viewState ->
                    _sectionViewState.value = viewState
                }
        }
    }

    /**
     * [CombinedSectionTypeModel]에 클릭 이벤트 적용
     * cf) [WishEvent]
     * 보일러 플레이트 코드가 생기지만, 다른 타입의 섹션(찜 동작이 필요없는 섹션 등)에 대한 확장성 고려
     */
    private fun CombinedSectionTypeModel.applyClicked() {
        when (this) {
            is CombinedSectionTypeModel.VerticalSection -> {
                data.products.map {
                    it.applyOnWishClicked { productId, isWish ->
                        wishClicked(productId, isWish)
                    }
                }
            }

            is CombinedSectionTypeModel.HorizontalSection -> {
                data.products.map {
                    it.applyOnWishClicked { productId, isWish ->
                        wishClicked(productId, isWish)
                    }
                }
            }

            is CombinedSectionTypeModel.GridSection -> {
                data.products.map {
                    it.applyOnWishClicked { productId, isWish ->
                        wishClicked(productId, isWish)
                    }
                }
            }
        }
    }

    /**
     * 찜 클릭 이벤트 구현부
     *
     * @param productId 상품 Id
     * @param isWish 해당 상품 찜 여부
     */
    private fun wishClicked(productId: Int, isWish: Boolean) {
        val inverseWish = !isWish

        _sectionViewState.update { currentState ->
            if (currentState !is SectionViewState.Success) {
                return
            }

            currentState.copy(
                combinedSections = currentState.combinedSections
                    .map { typeModel ->
                        updateItemIfWishChanged(
                            typeModel,
                            productId,
                            inverseWish
                        )
                    }
            )
        }
    }

    /**
     * 찜 상태 업데이트
     *
     * @param typeModel [CombinedSectionTypeModel]
     * @param productId 찜 이벤트가 발생한 product Id
     * @param inverseWish 바뀔 찜 상태
     * @return 상태 업데이트 된 [CombinedSectionTypeModel]
     */
    private fun updateItemIfWishChanged(
        typeModel: CombinedSectionTypeModel,
        productId: Int,
        inverseWish: Boolean
    ): CombinedSectionTypeModel {
        return getUpdatedSection(
            typeModel,
            typeModel.getData().products.containsProductId(productId),
            productId,
            inverseWish
        )
    }

    /**
     * [CombinedSectionTypeModel]에 대한 찜 상태 처리 후 반환
     *
     * @param typeModel [CombinedSectionTypeModel]
     * @param isUpdateItem 업데이트 여부
     * @param productId 찜 이벤트가 발생한 product Id
     * @param inverseWish 바뀔 찜 상태
     * @return 상태 처리 된 [CombinedSectionTypeModel]
     */
    private fun getUpdatedSection(
        typeModel: CombinedSectionTypeModel,
        isUpdateItem: Boolean,
        productId: Int,
        inverseWish: Boolean
    ): CombinedSectionTypeModel {
        if (!isUpdateItem) {
            return typeModel
        }

        return when (typeModel) {
            is CombinedSectionTypeModel.VerticalSection -> createUpdatedVerticalSectionForWish(typeModel, productId, inverseWish)
            is CombinedSectionTypeModel.HorizontalSection -> createUpdatedHorizontalSectionForWish(typeModel, productId, inverseWish)
            is CombinedSectionTypeModel.GridSection -> createUpdatedGridSectionForWish(typeModel, productId, inverseWish)
        }
    }

    /**
     * 찜 등록/해제 상태가 업데이트 된 [CombinedSectionTypeModel.VerticalSection] 반환
     *
     * @param typeModel [CombinedSectionTypeModel.VerticalSection]
     * @param productId 찜 이벤트가 발생한 product Id
     * @param inverseWish 바뀔 찜 상태
     * @return 찜 등록/해제 상태가 업데이트 된 [CombinedSectionTypeModel.VerticalSection]
     */
    private fun createUpdatedVerticalSectionForWish(
        typeModel: CombinedSectionTypeModel.VerticalSection,
        productId: Int,
        inverseWish: Boolean
    )
            : CombinedSectionTypeModel.VerticalSection {
        val data = typeModel.data

        val updatedProducts = data.products.map { product ->
            if (product.id == productId) {
                product.copy(
                    isWish = inverseWish
                ).applyOnWishClicked { productId, isWish ->
                    wishClicked(productId, isWish)
                }
            } else {
                product
            }
        }

        return typeModel.copy(
            data = data.copy(
                products = updatedProducts
            )
        )
    }

    /**
     * 찜 등록/해제 상태가 업데이트 된 [CombinedSectionTypeModel.HorizontalSection] 반환
     *
     * @param typeModel [CombinedSectionTypeModel.HorizontalSection]
     * @param productId 찜 이벤트가 발생한 product Id
     * @param inverseWish 바뀔 찜 상태
     * @return 찜 등록/해제 상태가 업데이트 된 [CombinedSectionTypeModel.HorizontalSection]
     */
    private fun createUpdatedHorizontalSectionForWish(
        typeModel: CombinedSectionTypeModel.HorizontalSection,
        productId: Int,
        inverseWish: Boolean
    )
            : CombinedSectionTypeModel.HorizontalSection {
        val data = typeModel.data

        val updatedProducts = data.products.map { product ->
            if (product.id == productId) {
                product.copy(
                    isWish = inverseWish
                ).applyOnWishClicked { productId, isWish ->
                    wishClicked(productId, isWish)
                }
            } else {
                product
            }
        }

        return typeModel.copy(
            data = data.copy(
                products = updatedProducts
            )
        )
    }

    /**
     * 찜 등록/해제 상태가 업데이트 된 [CombinedSectionTypeModel.GridSection] 반환
     *
     * @param typeModel [CombinedSectionTypeModel.GridSection]
     * @param productId 찜 이벤트가 발생한 product Id
     * @param inverseWish 바뀔 찜 상태
     * @return 찜 등록/해제 상태가 업데이트 된 [CombinedSectionTypeModel.GridSection]
     */
    private fun createUpdatedGridSectionForWish(
        typeModel: CombinedSectionTypeModel.GridSection,
        productId: Int,
        inverseWish: Boolean
    )
            : CombinedSectionTypeModel.GridSection {
        val data = typeModel.data

        val updatedProducts = data.products.map { product ->
            if (product.id == productId) {
                product.copy(
                    isWish = inverseWish
                ).applyOnWishClicked { productId, isWish ->
                    wishClicked(productId, isWish)
                }
            } else {
                product
            }
        }

        return typeModel.copy(
            data = data.copy(
                products = updatedProducts
            )
        )
    }

    /**
     * productId가 같은 product가 포함 되어 있는지 여부 반환
     *
     * @param productId 비교할 productId
     * @return productId 같은 product가 포함 되어 있는지 여부
     */
    private fun List<ProductUiModel>.containsProductId(productId: Int): Boolean {
        return this.any { it.id == productId }
    }

    /**
     * Paging을 위한 이전 리스트 저장
     *
     * @param state 새롭게 방출된 state
     */
    fun saveCurrentSectionsForPaging(state: SectionViewState.Success) {
        _currentSections = state.combinedSections
    }

    /**
     * 섹션 뷰 상태 관리 sealed interface
     */
    sealed interface SectionViewState {
        data object Loading : SectionViewState

        data object Error : SectionViewState

        data class Success(
            val combinedSections: List<CombinedSectionTypeModel>,
            val nextPage: Int
        ) : SectionViewState
    }
}