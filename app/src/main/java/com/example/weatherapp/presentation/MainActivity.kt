package com.example.weatherapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.data.network.api.ApiFactory
import com.example.weatherapp.data.repository.FavouriteRepositoryImpl
import com.example.weatherapp.domain.repository.FavouriteRepository
import com.example.weatherapp.domain.usecase.ChangeFavouriteStateUseCase
import com.example.weatherapp.domain.usecase.GetFavouriteCitiesIUseCase
import com.example.weatherapp.domain.usecase.SearchCityUseCase
import com.example.weatherapp.presentation.root.DefaultRootComponent
import com.example.weatherapp.presentation.root.RootContent
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var searchCityIUseCase: SearchCityUseCase

    @Inject
    lateinit var changeFavouriteStateUseCase: ChangeFavouriteStateUseCase

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as WeatherApp).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            searchCityIUseCase("пон").forEach {
                changeFavouriteStateUseCase.addToFavourite(it)
            }
        }

        setContent {
            RootContent(component = rootComponentFactory.create(defaultComponentContext()))
        }
    }
}