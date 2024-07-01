package com.asi.airport.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirportViewmodel @Inject constructor(private val airportRepo: AirportRepo) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uistate: StateFlow<UiState> = _uiState


    fun callAirport(code: String) {
        viewModelScope.launch {
            try {
                Log.d("ViewModel", "Calling airportRepo.callAirpot with code: $code")
                airportRepo.callAirpot(code).collectLatest { databaseResult ->
                    _uiState.update {
                        it.copy(airport = databaseResult.firstOrNull()?.toAirpot())
                    }
                }


            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        errorMessage = e.message
                    )
                }

            }
        }
    }


    fun AirportEntity.toAirpot(): Airport = Airport(
        id = id,
        iata_code = iata_code,
        name = name,
        passengers = passengers
    )


    data class UiState(
        var isSearchFieldEmpty: Boolean = false,
        var airport: Airport? = null,
        var errorMessage: String? = null
    )


}