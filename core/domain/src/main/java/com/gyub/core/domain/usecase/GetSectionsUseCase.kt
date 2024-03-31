package com.gyub.core.domain.usecase

import com.gyub.core.common.di.IoDispatcher
import com.gyub.core.domain.model.SectionsEntity
import com.gyub.core.domain.repository.SectionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * 섹션 리스트 가져오는 UseCase
 *
 * @author   Gyub
 * @created  2024/03/30
 */
class GetSectionsUseCase @Inject constructor(
    private val repository: SectionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    operator fun invoke(page: Int): Flow<SectionsEntity> = flow {
        val item = repository.getSections(page)
        emit(item)
    }.flowOn(ioDispatcher)
}