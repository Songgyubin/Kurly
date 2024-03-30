package com.gyub.core.domain.usecase

import com.gyub.core.domain.model.ProductEntity
import com.gyub.core.domain.model.base.Resource
import com.gyub.core.domain.repository.SectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 각 섹션의 상품 리스트 가져오는 UseCase
 *
 * @author   Gyub
 * @created  2024/03/30
 */
class GetSectionProductsUseCase @Inject constructor(
    private val sectionRepository: SectionRepository
) {
    operator fun invoke(sectionId: Int): Flow<Resource<List<ProductEntity>>> = flow {
        val item = sectionRepository.getSectionProducts(sectionId)
        emit(item)
    }.catch {
        emit(Resource.Error)
    }
}