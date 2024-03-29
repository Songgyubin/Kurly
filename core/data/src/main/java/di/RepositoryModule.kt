package di

import com.gyub.core.domain.repository.SectionRepository
import dagger.Binds
import repository.SectionRepositoryImpl

/**
 * Repository Module
 *
 * @author   Gyub
 * @created  2024/03/29
 */
abstract class RepositoryModule {

    @Binds
    abstract fun bindsSectionRepository(sectionRepositoryImpl: SectionRepositoryImpl): SectionRepository
}