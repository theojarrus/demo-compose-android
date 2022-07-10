package com.theost.composeapp.ui.di.profile

import com.theost.composeapp.presentation.profile.ProfileViewModel
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {

    @Provides
    fun provideProfileViewModel(): ProfileViewModel {
        return ProfileViewModel()
    }
}
