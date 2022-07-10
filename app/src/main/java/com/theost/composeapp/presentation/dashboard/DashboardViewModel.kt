package com.theost.composeapp.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theost.composeapp.domain.data.repository.FilmsRepository
import com.theost.composeapp.domain.data.state.Status.*
import com.theost.composeapp.ui.screens.dashboard.model.mapToFilmUi
import java.util.concurrent.TimeUnit.MILLISECONDS

class DashboardViewModel(private val filmsRepository: FilmsRepository) : ViewModel() {

    private val _state = MutableLiveData(DashboardState())
    val state: LiveData<DashboardState> = _state

    init {
        loadData()
    }

    fun loadData() {
        _state.postValue(state.value?.copy(status = Loading))
        state.value?.page?.let { page ->
            filmsRepository.getPopularFilms(page).subscribe({ node ->
                _state.postValue(
                    state.value?.copy(
                        status = Success,
                        pagesCount = node.pages,
                        data = node.items.map { it.mapToFilmUi() }
                    )
                )
            }, { cause ->
                _state.postValue(state.value?.copy(status = Error(cause), data = emptyList()))
            })
        }
    }

    fun loadMoreData() {
        if (state.value?.status is Success) {
            _state.postValue(state.value?.copy(status = Updating))
            state.value?.page?.let { page ->
                val next = page + 1
                val max = state.value?.pagesCount ?: 0
                if (next <= max) {
                    filmsRepository.getPopularFilms(next).delay(1000, MILLISECONDS).subscribe({ node ->
                        _state.postValue(
                            state.value?.copy(
                                status = Success,
                                pagesCount = node.pages,
                                page = next,
                                data = node.items.map { it.mapToFilmUi() }.toMutableList().apply {
                                    addAll(0, state.value?.data ?: emptyList())
                                })
                        )
                    }, { cause ->
                        _state.postValue(state.value?.copy(status = Error(cause)))
                    })
                } else {
                    _state.postValue(state.value?.copy(status = Loaded))
                }
            }
        }
    }
}
