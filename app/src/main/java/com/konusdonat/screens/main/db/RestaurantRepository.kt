package com.konusdonat.screens.main.db

import com.konusdonat.screens.main.data.CatalogueResponse
import com.konusdonat.screens.main.data.RemoteRestaurant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val restaurantDao: RestaurantDao
)
{
    suspend fun fetchCatalog(): Flow<CatalogueResponse> {
        return flow {
            val cacheNearest = restaurantDao.getAllNearest()
            val cachePopular = restaurantDao.getAllPopular()
            if (cacheNearest.isNotEmpty() || cachePopular.isNotEmpty()) {
                val listNearest: List<RemoteRestaurant> =
                    if (cacheNearest.isNotEmpty())
                        cacheNearest.map { it.mapToRemoteRestaurant() }
                    else
                        emptyList()

                val listPopular: List<RemoteRestaurant> =
                    if (cachePopular.isNotEmpty())
                        cachePopular.map { it.mapToRemoteRestaurant() }
                    else
                        emptyList()
                emit(
                    CatalogueResponse(
                        nearest = listNearest,
                        popular = listPopular,
                        commercial = null
                    )
                )
            }


            try {
                val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                    method = HttpMethod.Get
                }.body<CatalogueResponse>()

                restaurantDao.insertAll(*response.nearest.map {
                    it.mapToRestaurantEntityNearest()
                }.toTypedArray())
                restaurantDao.insertAll(*response.popular.map {
                    it.mapToRestaurantEntityPopular()
                }.toTypedArray())


                emit(response)
            } catch (e: Exception) {
                val message = e.message
            }
        }
    }
}