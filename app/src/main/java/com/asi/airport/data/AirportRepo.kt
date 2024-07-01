package com.asi.airport.data

import kotlinx.coroutines.flow.Flow

interface AirportRepo {

    fun callAirpot(code:String) : Flow<List<AirportEntity>>

}