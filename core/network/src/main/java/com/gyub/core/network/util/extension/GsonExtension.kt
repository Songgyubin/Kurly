package com.gyub.core.network.util.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Gson 확장함수
 *
 * @author   Gyub
 * @created  2024/03/29
 */

/**
 * Json으로부터 [T] 추출
 */
internal inline fun <reified T> Gson.fromJson(json: String): T? {
    return fromJson(json, object : TypeToken<T>() {}.type)
}