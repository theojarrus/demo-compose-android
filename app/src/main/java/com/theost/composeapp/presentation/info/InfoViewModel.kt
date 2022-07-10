package com.theost.composeapp.presentation.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theost.composeapp.domain.data.repository.FilmsRepository
import com.theost.composeapp.domain.data.state.Status.*
import com.theost.composeapp.ui.screens.dashboard.model.mapToFilmUi

class InfoViewModel(private val filmsRepository: FilmsRepository) : ViewModel() {

    private val _state = MutableLiveData(InfoState())
    val state: LiveData<InfoState> = _state

    fun loadData(id: Int) {
        if (state.value?.status == Initial) {
            _state.postValue(state.value?.copy(status = Loading))
            filmsRepository.getFilm(id).subscribe({ data ->
                _state.postValue(state.value?.copy(status = Success, data = data.mapToFilmUi()))
            }, { cause ->
                _state.postValue(state.value?.copy(status = Error(cause)))
                cause.printStackTrace()
            })
        }
    }
}
