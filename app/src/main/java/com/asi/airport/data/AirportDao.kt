package com.asi.airport.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AirportDao {

    @Query("Select * from airport Where iata_code = :code")
    fun getTheAirport(code: String) : Flow<List<AirportEntity>>

}
