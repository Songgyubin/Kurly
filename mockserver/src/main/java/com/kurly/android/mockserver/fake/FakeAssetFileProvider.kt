package com.kurly.android.mockserver.fake

import javax.inject.Inject

/**
 * 테스트를 위한 Fake Asset File Provider
 *
 * @author   Gyub
 * @created  2024/03/28
 */
class FakeAssetFileProvider @Inject constructor() : FakeFileProvider {

    /**
     * filePath로부터 파일 읽어오기
     */
    override fun getJsonFromAsset(filePath: String): String? {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath) ?: return null
        return inputStream.bufferedReader().use { it.readText() }
    }
}