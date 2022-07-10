package com.theost.composeapp.domain.di

import com.theost.composeapp.domain.data.repository.FilmsRepository
import com.theost.composeapp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun filmsRepository(): FilmsRepository

    @Component.Factory
    interface Factory {

        fun create(): AppComponent
    }
}
