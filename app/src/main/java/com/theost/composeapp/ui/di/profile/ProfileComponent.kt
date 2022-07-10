package com.theost.composeapp.ui.di.profile

import com.theost.composeapp.domain.di.AppComponent
import com.theost.composeapp.presentation.profile.ProfileViewModel
import com.theost.composeapp.ui.di.annotation.ScreenScope
import dagger.Component

@ScreenScope
@Component(modules = [ProfileModule::class], dependencies = [AppComponent::class])
interface ProfileComponent {

    fun getViewModel(): ProfileViewModel

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ProfileComponent
    }
}
