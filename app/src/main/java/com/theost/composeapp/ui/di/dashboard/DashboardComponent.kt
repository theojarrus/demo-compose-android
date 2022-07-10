package com.theost.composeapp.ui.di.dashboard

import com.theost.composeapp.domain.di.AppComponent
import com.theost.composeapp.presentation.dashboard.DashboardViewModel
import com.theost.composeapp.ui.di.annotation.ScreenScope
import dagger.Component

@ScreenScope
@Component(modules = [DashboardModule::class], dependencies = [AppComponent::class])
interface DashboardComponent {

    fun getViewModel(): DashboardViewModel

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): DashboardComponent
    }
}
