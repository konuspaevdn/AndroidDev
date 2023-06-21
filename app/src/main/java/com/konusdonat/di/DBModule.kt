package com.konusdonat.di

import android.content.Context
import androidx.room.Room
import com.konusdonat.screens.main.db.RestaurantDao
import com.konusdonat.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    fun provideDB(@ApplicationContext context: Context) : AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "food_del")
            .build()

    @Provides
    fun provideRestaurantsDao(appDatabase: AppDataBase): RestaurantDao =
        appDatabase.restaurantDao()
}