package com.konusdonat.screens.main.data

import kotlinx.serialization.Serializable

@Serializable
data class CatalogueResponse(
    val nearest: List<RemoteRestaurant>,
    val popular: List<RemoteRestaurant>,
    val commercial: RemoteCommercial? = null
)

@Serializable
data class RemoteRestaurant(
    val id: Int,
    val name: String,
    val deliveryTime: String,
    val image: String
)

@Serializable
data class RemoteCommercial(
    val picture: String,
    val url: String
)