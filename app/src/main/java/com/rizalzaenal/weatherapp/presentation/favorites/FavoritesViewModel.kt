package com.rizalzaenal.weatherapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalzaenal.weatherapp.domain.model.Location
import com.rizalzaenal.weatherapp.domain.usecase.GetFavoriteLocationsUseCase
import com.rizalzaenal.weatherapp.domain.usecase.SetLatestLocationUseCase
import com.rizalzaenal.weatherapp.presentation.State
import com.rizalzaenal.weatherapp.utils.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteLocationsUseCase: GetFavoriteLocationsUseCase,
    private val setLatestLocationUseCase: SetLatestLocationUseCase
) : ViewModel() {
    private val _favoriteLocationsState = MutableStateFlow<State<List<Location>>>(State.Empty)
    val favoriteLocationsState: StateFlow<State<List<Location>>> = _favoriteLocationsState

    fun getFavoriteLocations() {
        _favoriteLocationsState.value = State.Loading
        viewModelScope.launch {
            try {
                val locations = getFavoriteLocationsUseCase()
                _favoriteLocationsState.value = State.Success(locations)
            } catch (e: Exception) {
                _favoriteLocationsState.value = getErrorMessage(e)
                _favoriteLocationsState.value = State.Empty
            }
        }
    }

    fun setLatestLocation(location: Location) {
        viewModelScope.launch {
            setLatestLocationUseCase(location)
        }
    }
}