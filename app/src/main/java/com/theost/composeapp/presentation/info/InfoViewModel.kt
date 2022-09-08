package com.theost.composeapp.presentation.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theost.composeapp.domain.data.repository.FilmsRepository
import com.theost.composeapp.domain.data.state.Status.*
import com.theost.composeapp.ui.screens.dashboard.model.mapToFilmUi
import kotlinx.coroutines.launch

class InfoViewModel(private val filmsRepository: FilmsRepository) : ViewModel() {

    private val _state = MutableLiveData(InfoState())
    val state: LiveData<InfoState> = _state

    fun loadData(id: Int) {
        if (state.value?.status == Initial) {
            viewModelScope.launch {
                _state.postValue(state.value?.copy(status = Loading))
                try {
                    val film = filmsRepository.getFilm(id).mapToFilmUi()
                    _state.postValue(state.value?.copy(status = Success, data = film))
                } catch (exception: Exception) {
                    _state.postValue(state.value?.copy(status = Error(exception)))
                    exception.printStackTrace()
                }
            }
        }
    }
}
