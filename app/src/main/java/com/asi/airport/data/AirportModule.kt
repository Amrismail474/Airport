package com.asi.airport.data

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AirportModule {

    @Provides
    @Singleton
    fun createDB(@ApplicationContext context: Context) : AirportDB{
        return Room.databaseBuilder(
            context,
            AirportDB::class.java,
            "Schedule.db"
        ).createFromAsset("database/flight_search.db").build()
    }

    @Provides
    fun createDao(db : AirportDB): AirportDao{
       return db.airportDao()
    }
}



@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {
    @Binds
     fun createRepo( airPortOfflineRepo : AirportOfflineRepo) : AirportRepo
}