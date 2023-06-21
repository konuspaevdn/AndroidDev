package com.konusdonat.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.konusdonat.screens.main.db.RestaurantDao
import com.konusdonat.screens.main.db.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}