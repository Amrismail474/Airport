package com.asi.airport.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirportViewmodel @Inject constructor(val airportRepo: AirportRepo):ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uistate : StateFlow<UiState> = _uiState

     fun callAirport(code: String){
         viewModelScope.launch{
             try {
                 Log.d("ViewModel", "Calling airportRepo.callAirpot with code: $code")
                 val AirportUIState =  airportRepo.callAirpot(code)

                     .map { airportEntity ->
                         Log.d("ViewModel", "Mapping airportEntity: $airportEntity")
                         airportEntity.toAirpot() }.firstOrNull()

                 Log.d("ViewModel", "AirportUIState: $AirportUIState")
                 _uiState.value =UiState(airport = AirportUIState)
             }catch (e: Exception) {
                 _uiState.value = UiState(errorMessage = e.message)
             }
         }
    }


    fun AirportEntity.toAirpot():Airport=Airport(
        id = id,
        iata_code = iata_code,
        name=name,
        passengers = passengers
    )


    data class UiState(
        var isSearchFieldEmpty: Boolean = false,
        var airport: Airport? = null,
        var errorMessage: String? = null
    )



}