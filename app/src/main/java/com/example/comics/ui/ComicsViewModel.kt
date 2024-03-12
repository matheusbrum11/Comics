package com.example.comics.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.data.model.ItemModel
import com.example.comics.data.repository.ComicsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ComicsViewModel(private val repository: ComicsRepository): ViewModel() {
    private val _comicsResponse = MutableStateFlow<UiStateResponse<List<UiItemModel>>>(UiStateResponse.Loading)
    val comicsResponse: StateFlow<UiStateResponse<List<UiItemModel>>> get() = _comicsResponse
    init {
        getComics()
    }

     fun getComics() {
        viewModelScope.launch {
            repository.getComics()
                .onStart {
                    _comicsResponse.value = UiStateResponse.Loading
                    delay(100)
                }
                .catch { _comicsResponse.value = UiStateResponse.Error(it) }
                .collect{
                    _comicsResponse.value = UiStateResponse.Success(it.mapDataModelToUiModel())
                }
        }
    }

}