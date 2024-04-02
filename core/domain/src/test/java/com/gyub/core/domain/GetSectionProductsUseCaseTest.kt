package com.gyub.core.domain

import com.gyub.core.domain.fake.FakeSectionRepository
import com.gyub.core.domain.usecase.GetSectionProductsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * 섹션 리스트 가져오는 UseCase
 *
 * @author   Gyub
 * @created  2024/03/29
 */
class GetSectionProductsUseCaseTest {

    private lateinit var getSectionProductsDataUseCase: GetSectionProductsUseCase
    private val sectionRepository = FakeSectionRepository()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        getSectionProductsDataUseCase = GetSectionProductsUseCase(sectionRepository, testDispatcher)
    }

    @Test
    fun `verify combine sections and section products`() = runTest {
        getSectionProductsDataUseCase(1).first().run {
            assertTrue(this.isNotEmpty())

            val firstItem = this.first()

            assertEquals(5063110, firstItem.id)
        }
    }

}