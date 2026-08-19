package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.entity.City
import kotlinx.coroutines.flow.Flow
//import com.example.weatherapp.BuildConfig

interface FavouriteRepository {

    fun observeIsFavourite(cityId: Int): Flow<Boolean>

    suspend fun addToFavourite(city: City)

    suspend fun removeFromFavourite(cityId: Int)

    val favouriteCities: Flow<City>
}