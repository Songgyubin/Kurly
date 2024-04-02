package com.gyub.core.domain

import com.gyub.core.common.result.Result
import com.gyub.core.domain.fake.FakeSectionRepository
import com.gyub.core.domain.model.CombinedSectionEntity
import com.gyub.core.domain.model.ProductEntity
import com.gyub.core.domain.model.SectionEntity
import com.gyub.core.domain.usecase.GetCombinedSectionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.text.Typography.section

/**
 *
 *
 * @author   Gyub
 * @created  2024/04/03
 */
@ExperimentalCoroutinesApi
class GetCombinedSectionUseCaseTest {

    private val repository = FakeSectionRepository()
    private val ioDispatcher = Dispatchers.Unconfined
    private val getCombinedSectionUseCase = GetCombinedSectionUseCase(repository, ioDispatcher)

    @Test
    fun `when getSections returns data, expect success result`() = runTest {
        getCombinedSectionUseCase(1)
            .collect {
                if (it is Result.Success) {
                    assertTrue(
                        it.data.first == listOf(
                            CombinedSectionEntity(
                                section = SectionEntity(
                                    title = "함께하면 더 좋은 상품",
                                    id = 1,
                                    type = "grid",
                                    url = "section_products_1"
                                ), products = listOf(
                                    ProductEntity(
                                        id = 5063110,
                                        name = "[연세우유 x 마켓컬리] 전용목장우유 900mL",
                                        image = "https://img-cf.kurly.com/shop/data/goods/1637154205701l0.jpg",
                                        originalPrice = 2070,
                                        discountedPrice = 1800,
                                        isSoldOut = false
                                    )
                                )
                            )
                        )
                    )
                }
            }
    }

    @Test
    fun `when page is 0 getSections throws exception, expect error result`() = runTest {
        getCombinedSectionUseCase(0)
            .collect {
                if (it !is Result.Loading) {
                    assertTrue(it is Result.Error)
                }
            }
    }

    @Test
    fun `when getSections returns null or section's id is null throws exception, expect error result`() = runTest {
        getCombinedSectionUseCase(1)
            .collect {
                if (it !is Result.Loading) {
                    println(it)
                    assertTrue(it is Result.Error)
                }
            }
    }
}