package com.theost.composeapp.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theost.composeapp.domain.data.model.User
import com.theost.composeapp.domain.data.state.Status.Success

class ProfileViewModel : ViewModel() {

    private val _state = MutableLiveData(ProfileState())
    val state: LiveData<ProfileState> = _state

    init {
        val user = User("Theo Jedi", "f.sedov@tinkoff.ru", "https://sun1-13.userapi.com/s/v1/ig2/p-DcrNDsy_ys4snIUxob4yKYSGx1jBx2gvcVl2j3KX7WgSiPIPS1XeXL_ZKV-8q9bLlxUpTtfXAMjZ42sFio2aDF.jpg?size=200x200&quality=96&crop=479,0,1707,1707&ava=1")
        _state.value = state.value?.run { copy(status = Success, data = user) }
    }
}
