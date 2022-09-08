package com.theost.composeapp.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theost.composeapp.domain.data.model.Film
import com.theost.composeapp.domain.data.repository.FilmsRepository
import com.theost.composeapp.domain.data.state.Status.*
import com.theost.composeapp.ui.screens.dashboard.model.mapToFilmUi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit.MILLISECONDS

class DashboardViewModel(private val filmsRepository: FilmsRepository) : ViewModel() {

    private val _state = MutableLiveData(DashboardState())
    val state: LiveData<DashboardState> = _state

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _state.postValue(state.value?.copy(status = Loading))
            state.value?.page?.let { page ->
                try {
                    val node = filmsRepository.getPopularFilms(page)
                    _state.postValue(
                        state.value?.copy(
                            status = Success,
                            pagesCount = node.pages,
                            data = node.items.map(Film::mapToFilmUi)
                        )
                    )
                } catch (exception: Exception) {
                    _state.postValue(
                        state.value?.copy(
                            status = Error(exception),
                            data = emptyList()
                        )
                    )
                }
            }
        }
    }

    fun loadMoreData() {
        viewModelScope.launch {
            if (state.value?.status is Success) {
                _state.postValue(state.value?.copy(status = Updating))
                state.value?.page?.let { page ->
                    val next = page + 1
                    val max = state.value?.pagesCount ?: 0
                    if (next <= max) {
                        try {
                            val node = filmsRepository.getPopularFilms(next)
                            delay(5000)
                            _state.postValue(
                                state.value?.copy(
                                    status = Success,
                                    pagesCount = node.pages,
                                    page = next,
                                    data = node.items.map(Film::mapToFilmUi).toMutableList().apply {
                                        addAll(0, state.value?.data ?: emptyList())
                                    })
                            )
                        } catch (exception: Exception) {
                            _state.postValue(state.value?.copy(status = Error(exception)))
                        }
                    } else {
                        _state.postValue(state.value?.copy(status = Loaded))
                    }
                }
            }
        }
    }
}
