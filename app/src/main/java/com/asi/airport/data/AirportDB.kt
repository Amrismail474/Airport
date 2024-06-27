package com.asi.airport.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AirportEntity::class], version = 1, exportSchema = false)
abstract class AirportDB : RoomDatabase() {
    abstract fun airportDao() : AirportDao

}