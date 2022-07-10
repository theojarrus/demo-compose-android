package com.theost.composeapp.ui.di.dashboard

import com.theost.composeapp.domain.data.repository.FilmsRepository
import com.theost.composeapp.presentation.dashboard.DashboardViewModel
import dagger.Module
import dagger.Provides

@Module
class DashboardModule {

    @Provides
    fun provideDashboardViewModel(filmsRepository: FilmsRepository): DashboardViewModel {
        return DashboardViewModel(filmsRepository)
    }
}
