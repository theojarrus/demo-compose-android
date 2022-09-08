package com.theost.composeapp.domain.di

import com.theost.composeapp.domain.data.repository.FilmsRepository
import com.theost.composeapp.domain.network.NetworkService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFilmsRepository(
        networkService: NetworkService,
        dispatcher: CoroutineDispatcher
    ): FilmsRepository {
        return FilmsRepository(networkService, dispatcher)
    }

    @Provides
    fun provideCoroutineDispatcher() : CoroutineDispatcher {
        return Dispatchers.IO
    }
}
