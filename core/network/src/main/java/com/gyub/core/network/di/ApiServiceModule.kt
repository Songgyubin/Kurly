package com.gyub.core.network.di

import com.gyub.core.network.retrofit.SectionApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

/**
 * 각 Api Service Module
 *
 * @author   Gyub
 * @created  2024/03/29
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun providesSectionApiService(retrofit: Retrofit.Builder): SectionApiService =
        retrofit.build()
            .create()
}