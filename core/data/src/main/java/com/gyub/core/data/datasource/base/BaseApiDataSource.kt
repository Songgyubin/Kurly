package com.gyub.core.data.datasource.base

import android.util.Log
import com.gyub.core.domain.model.base.Resource
import java.io.IOException
import javax.inject.Inject

/**
 * API 통신에 필요한 공통 처리 Class
 *
 * @author   Gyub
 * @created  2024/03/30
 */
open class BaseApiDataSource
@Inject
constructor() {
    inline fun <T> safeApiCall(apiCall: () -> T): Resource<T> {
        return try {
            val apiResponse = apiCall.invoke()
            Resource.Success(apiResponse)
        } catch (throwable: Throwable) {

            when (throwable) {
                is IOException -> {
                    Log.e("TAG", "safeApiCall: ", throwable.cause)
                    Resource.Failure
                }

                else -> {
                    Log.e("TAG", "safeApiCall: ", throwable)
                    Resource.Error
                }
            }
        }
    }
}