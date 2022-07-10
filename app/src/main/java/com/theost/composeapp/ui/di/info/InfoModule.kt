package com.theost.composeapp.ui.di.info

import com.theost.composeapp.domain.data.repository.FilmsRepository
import com.theost.composeapp.presentation.info.InfoViewModel
import dagger.Module
import dagger.Provides

@Module
class InfoModule {

    @Provides
    fun provideInfoViewModel(filmsRepository: FilmsRepository): InfoViewModel {
        return InfoViewModel(filmsRepository)
    }
}
