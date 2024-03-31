package com.gyub.core.domain.usecase

import com.gyub.core.common.di.IoDispatcher
import com.gyub.core.common.result.Result
import com.gyub.core.common.result.toResult
import com.gyub.core.common.util.extension.orDefault
import com.gyub.core.domain.model.CombinedSectionEntity
import com.gyub.core.domain.repository.SectionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author   Gyub
 * @created  2024/04/02
 */
class GetCombinedSectionUseCase @Inject constructor(
    private val repository: SectionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    operator fun invoke(page: Int): Flow<Result<Pair<List<CombinedSectionEntity>,Int>>> = flow {
        val sectionsEntity = repository.getSections(page)
        val sections = sectionsEntity.sections
        val nextPage = sectionsEntity.nextPage.orDefault()

        val combinedSections = coroutineScope {
            val deferredProducts = sections?.mapNotNull { section ->
                section.id?.let { id ->
                    async {
                        CombinedSectionEntity(
                            section,
                            repository.getSectionProducts(sectionId = id)
                        )
                    }
                }
            }
            deferredProducts!!.awaitAll() to nextPage
        }
        emit(combinedSections)
    }.toResult()
        .flowOn(ioDispatcher)
}