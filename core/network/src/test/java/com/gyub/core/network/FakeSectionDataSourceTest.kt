package com.gyub.core.network

import com.google.gson.Gson
import com.gyub.core.network.fake.FakeSectionsDataSource
import com.gyub.core.network.model.Product
import com.gyub.core.network.model.Section
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Test FakeSectionsDataSource
 *
 * @author   Gyub
 * @created  2024/03/28
 */
class FakeSectionsDataSourceTest {

    private lateinit var fakeSectionsDataSource: FakeSectionsDataSource
    private val gson = Gson()

    @Before
    fun setUp() {
        fakeSectionsDataSource = FakeSectionsDataSource(gson)
    }

    @Test
    fun `Test Get Section`() {
        val sections = fakeSectionsDataSource.getSections()

        assertEquals(
            Section(
                title = "함께하면 더 좋은 상품",
                id = 1,
                type = "grid",
                url = "section_products_1"
            ),
            sections?.firstOrNull()
        )
    }

    @Test
    fun `Test Get Section Product`() {
        val sectionProducts = fakeSectionsDataSource.getSectionProducts()

        assertEquals(
            Product(
                id = 5063110,
                name = "[연세우유 x 마켓컬리] 전용목장우유 900mL",
                image = "https://img-cf.kurly.com/shop/data/goods/1637154205701l0.jpg",
                originalPrice = 2070,
                discountedPrice = 1800,
                isSoldOut = false
            ),
            sectionProducts?.firstOrNull()
        )
    }
}