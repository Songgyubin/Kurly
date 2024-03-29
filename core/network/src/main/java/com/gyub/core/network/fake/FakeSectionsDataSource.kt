package com.gyub.core.network.fake

import com.google.gson.Gson
import com.gyub.core.network.model.Product
import com.gyub.core.network.model.Section
import com.gyub.core.network.model.base.NetworkResponse
import com.gyub.core.network.util.extension.fromJson
import javax.inject.Inject

/**
 * Fake Section Data Source
 *
 * @author   Gyub
 * @created  2024/03/28
 */
class FakeSectionsDataSource @Inject constructor(
    private val gson: Gson
) {

    fun getSections(): List<Section>? {
        val jsonString = getJsonFromAsset(SECTIONS_1)
        return gson.fromJson<NetworkResponse<List<Section>>>(jsonString)?.data
    }

    fun getSectionProducts(): List<Product>? {
        val jsonString = getJsonFromAsset(SECTION_PRODUCTS_1)
        return gson.fromJson<NetworkResponse<List<Product>>>(jsonString)?.data
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
