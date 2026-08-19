package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.entity.City
import com.example.weatherapp.domain.entity.Forecast
import com.example.weatherapp.domain.entity.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    val favouriteCities: Flow<City>

    suspend fun getWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast

}