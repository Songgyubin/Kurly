package com.gyub.core.domain.usecase

import com.gyub.core.common.di.IoDispatcher
import com.gyub.core.domain.model.ProductEntity
import com.gyub.core.domain.repository.SectionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * 각 섹션의 상품 리스트 가져오는 UseCase
 *
 * @author   Gyub
 * @created  2024/03/30
 */
class GetSectionProductsUseCase @Inject constructor(
    private val sectionRepository: SectionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    operator fun invoke(sectionId: Int): Flow<List<ProductEntity>> = flow {
        val item = sectionRepository.getSectionProducts(sectionId)
        emit(item)
    }.flowOn(ioDispatcher)
}