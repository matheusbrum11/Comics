package com.example.comics.ui

sealed class UiStateResponse<out T : Any> {
    object Loading : UiStateResponse<Nothing>()
    data class Success<out T : Any>(val result: T) : UiStateResponse<T>()
    data class Error(val error: Throwable) : UiStateResponse<Nothing>()
}

