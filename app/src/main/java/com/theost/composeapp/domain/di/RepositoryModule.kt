package com.theost.composeapp.domain.di

import com.theost.composeapp.domain.data.repository.FilmsRepository
import com.theost.composeapp.domain.network.NetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFilmsRepository(networkService: NetworkService): FilmsRepository {
        return FilmsRepository(networkService)
    }
}
