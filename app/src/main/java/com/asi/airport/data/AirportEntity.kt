package com.asi.airport.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("airport")
data class AirportEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var iata_code : String,
    var name : String,
    var passengers : Int
)
