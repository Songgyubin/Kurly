package com.gyub.core.network.fake

import com.google.gson.Gson
import com.gyub.core.network.model.Item
import com.gyub.core.network.model.SectionProducts
import com.gyub.core.network.model.Sections
import javax.inject.Inject

/**
 *
 *
 * @author   Gyub
 * @created  2024/03/28
 */
class FakeSectionsDataSource @Inject constructor(
    private val gson: Gson
) {

    fun getSections(): Sections {
        val jsonString = getJsonFromAsset(SECTIONS_1)
        return gson.fromJson(jsonString, Sections::class.java)
    }

    fun getSectionProducts(): SectionProducts {
        val jsonString = getJsonFromAsset(SECTION_PRODUCTS_1)
        return gson.fromJson(jsonString, SectionProducts::class.java)
    }

    private fun getJsonFromAsset(filePath: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath) ?: return ""

        return inputStream.bufferedReader().use { it.readText() }
    }

    companion object {
        private const val SECTIONS_1 = "sections/sections_1.json"
        private const val SECTION_PRODUCTS_1 = "products/section_products_1.json"
    }
}