package com.rizalzaenal.weatherapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalzaenal.weatherapp.domain.model.Location
import com.rizalzaenal.weatherapp.domain.usecase.GetLocationsUseCase
import com.rizalzaenal.weatherapp.domain.usecase.SetLatestLocationUseCase
import com.rizalzaenal.weatherapp.presentation.State
import com.rizalzaenal.weatherapp.utils.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val setLatestLocationUseCase: SetLatestLocationUseCase
) : ViewModel() {
    private val _searchState = MutableStateFlow<State<List<Location>>>(State.Empty)
    val searchState: StateFlow<State<List<Location>>> = _searchState

    fun getLocations(query: String) {
        _searchState.value = State.Loading
        viewModelScope.launch {
            try {
                val locations = getLocationsUseCase(query)
                _searchState.value = State.Success(locations)
            } catch (e: Exception) {
                _searchState.value = getErrorMessage(e)
                //to emit error value only once, error state replaced after emitted
                _searchState.value = State.Empty
            }
        }
    }

    fun setLatestLocation(location: Location) {
        viewModelScope.launch {
            setLatestLocationUseCase(location)
        }
    }

}