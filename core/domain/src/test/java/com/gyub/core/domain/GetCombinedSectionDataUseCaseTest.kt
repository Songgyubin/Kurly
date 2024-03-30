package com.gyub.core.domain

import com.gyub.core.domain.fake.FakeSectionRepository
import com.gyub.core.domain.usecase.GetCombinedSectionDataUseCase
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * [Test] 섹션 데이터와 섹션 별 상품 리스트가 결합된 데이터 가져오는 UseCase
 *
 * @author   Gyub
 * @created  2024/03/29
 */
class GetCombinedSectionDataUseCaseTest {

    private lateinit var getCombinedSectionDataUseCase: GetCombinedSectionDataUseCase
    private val sectionRepository = FakeSectionRepository()

    @Before
    fun setUp() {
        getCombinedSectionDataUseCase = GetCombinedSectionDataUseCase(sectionRepository)
    }

    @Test
    fun `verify combine sections and section products`() = runTest {
        val combinedData = getCombinedSectionDataUseCase(1).toList().firstOrNull()

        assertNotNull(combinedData)

        assertTrue(combinedData!!.isNotEmpty())

        val firstItem = combinedData.first()

        assertEquals(1,firstItem.section?.id)
        assertEquals(5063110, firstItem.products?.firstOrNull()?.id)
    }

}