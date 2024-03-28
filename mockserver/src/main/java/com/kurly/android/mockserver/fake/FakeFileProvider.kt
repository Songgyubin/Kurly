package com.kurly.android.mockserver.fake

/**
 * 테스트를 위한 Fake File Provider
 *
 * @author   Gyub
 * @created  2024/03/28
 */
interface FakeFileProvider {
    fun getJsonFromAsset(filePath: String): String?
}