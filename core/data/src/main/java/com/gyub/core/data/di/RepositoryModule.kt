package com.gyub.core.data.di

import com.gyub.core.data.repository.SectionRepositoryImpl
import com.gyub.core.domain.repository.SectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Repository Module
 *
 * @author   Gyub
 * @created  2024/03/29
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsSectionRepository(sectionRepositoryImpl: SectionRepositoryImpl): SectionRepository
}