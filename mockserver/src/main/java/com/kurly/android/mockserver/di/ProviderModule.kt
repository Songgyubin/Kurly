package com.kurly.android.mockserver.di

import com.kurly.android.mockserver.fake.FakeAssetFileProvider
import com.kurly.android.mockserver.fake.FakeFileProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Provider Module
 *
 * @author   Gyub
 * @created  2024/03/28
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ProviderModule {
    @Binds
    abstract fun bindsFakeAssetFileProvider(fakeAssetFileProvider: FakeAssetFileProvider): FakeFileProvider
}