package com.gyub.core.network.retrofit

import com.gyub.core.network.const.EndPoints.SECTIONS
import com.gyub.core.network.const.EndPoints.SECTION_PRODUCTS
import com.gyub.core.network.model.Product
import com.gyub.core.network.model.Section
import com.gyub.core.network.model.base.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Section 관련 API
 *
 * @author   Gyub
 * @created  2024/03/28
 */
interface SectionApiService {

    /**
     * Sections 정보 가져오기
     */
    @GET(SECTIONS)
    suspend fun getSections(@Query("page") page: Int): NetworkResponse<List<Section>>

    /**
     * Section 별 상품 리스트 가져오기
     */
    @GET(SECTION_PRODUCTS)
    suspend fun getSectionProducts(@Query("sectionId") sectionId: Int): NetworkResponse<List<Product>>

}