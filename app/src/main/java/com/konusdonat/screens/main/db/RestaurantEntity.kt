package com.konusdonat.screens.main.db

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.konusdonat.screens.main.data.RemoteRestaurant

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "deliveryTime") val time: String,
    @ColumnInfo(name = "category") val category: String
)


fun RemoteRestaurant.mapToRestaurantEntityNearest(): RestaurantEntity {
    return RestaurantEntity(
        id = id, name = name, logo = image, time = deliveryTime,
        category = "nearest")
}

fun RemoteRestaurant.mapToRestaurantEntityPopular(): RestaurantEntity {
    return RestaurantEntity(id = id * 1000, name = name, logo = image, time = deliveryTime,
        category = "popular")
}



fun RestaurantEntity.mapToRemoteRestaurant(): RemoteRestaurant =
    RemoteRestaurant(id = id, name = name, image = logo, deliveryTime = time)

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants")
    suspend fun getAll(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurants WHERE category='nearest'")
    suspend fun getAllNearest(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurants WHERE category='popular'")
    suspend fun getAllPopular(): List<RestaurantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg restaurants: RestaurantEntity)

}