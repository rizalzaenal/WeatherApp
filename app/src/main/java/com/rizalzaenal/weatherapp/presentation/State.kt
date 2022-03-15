package com.rizalzaenal.weatherapp.presentation

sealed class State<out T> {
    object Loading : State<Nothing>()
    object Init : State<Nothing>()
    data class Error(var message: String) : State<Nothing>()
    data class Success<T>(var data: T) : State<T>()
}