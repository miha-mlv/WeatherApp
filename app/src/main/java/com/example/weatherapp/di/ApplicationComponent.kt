package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.domain.repository.FavouriteRepository
import com.example.weatherapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [DataModule::class,
        PresentationModule::class]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun favouriteRepository(): FavouriteRepository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}