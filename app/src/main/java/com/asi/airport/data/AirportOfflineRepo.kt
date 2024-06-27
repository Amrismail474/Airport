package com.asi.airport.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AirportOfflineRepo @Inject constructor(val airportDao: AirportDao) : AirportRepo {

    override fun callAirpot(code: String): Flow<AirportEntity> {
        Log.d("AirportRepo", "Fetching airport with code: $code from database")
        return airportDao.getTheAirport(code)
    }

}

