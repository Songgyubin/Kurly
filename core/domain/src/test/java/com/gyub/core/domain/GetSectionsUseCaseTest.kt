package com.gyub.core.domain

import com.gyub.core.domain.fake.FakeSectionRepository
import com.gyub.core.domain.usecase.GetSectionsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * 섹션 리스트 가져오는 UseCase
 *
 * @author   Gyub
 * @created  2024/03/29
 */
class GetSectionsUseCaseTest {

    private lateinit var getSectionsUseCase: GetSectionsUseCase
    private val sectionRepository = FakeSectionRepository()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        getSectionsUseCase = GetSectionsUseCase(sectionRepository, testDispatcher)
    }

    @Test
    fun `verify combine sections and section products`() = runTest {
        getSectionsUseCase(1).first().run {
            val sections = this.sections
            assertNotNull(sections)
            assertTrue(sections!!.isNotEmpty())

            val firstItem = sections.first()

            assertEquals(1, firstItem.id)
        }
    }
}