package com.kurly.android.mockserver

import com.kurly.android.mockserver.fake.FakeFileProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
internal class MockServerTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fileProvider: FakeFileProvider

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testReadResourceFile() {
        val result = fileProvider.getJsonFromAsset("file_read_test.json")
        println(result)
        assert(null != result)
    }
}
