package com.asi.airport.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AirportOfflineRepo @Inject constructor(val airportDao: AirportDao) : AirportRepo {

    override fun callAirpot(code: String): Flow<List<AirportEntity>> =  airportDao.getTheAirport(code)

}

