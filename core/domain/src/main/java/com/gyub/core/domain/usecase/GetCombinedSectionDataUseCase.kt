package com.gyub.core.domain.usecase

import com.gyub.core.domain.model.CombinedSectionEntity
import com.gyub.core.domain.repository.SectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 섹션 데이터와 섹션 별 상품 리스트가 결합된 데이터 가져오는 UseCase
 *
 * @author   Gyub
 * @created  2024/03/29
 */
class GetCombinedSectionDataUseCase @Inject constructor(
    private val sectionRepository: SectionRepository
) {
    operator fun invoke(page: Int): Flow<List<CombinedSectionEntity>> = flow {
        val sections = sectionRepository.getSections(page)

        val combinedSections = sections.mapNotNull { section ->
            val products = section.id?.let { sectionRepository.getSectionProducts(it) }
            if (products != null) {
                CombinedSectionEntity(section, products)
            } else {
                null
            }
        }

        emit(combinedSections)
    }
}