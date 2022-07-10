package com.theost.composeapp.ui.di.info

import com.theost.composeapp.domain.di.AppComponent
import com.theost.composeapp.presentation.info.InfoViewModel
import com.theost.composeapp.ui.di.annotation.ScreenScope
import dagger.Component

@ScreenScope
@Component(modules = [InfoModule::class], dependencies = [AppComponent::class])
interface InfoComponent {

    fun getViewModel(): InfoViewModel

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): InfoComponent
    }
}
