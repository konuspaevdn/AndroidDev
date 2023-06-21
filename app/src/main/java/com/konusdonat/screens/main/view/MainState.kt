package com.konusdonat.screens.main.view

import com.konusdonat.screens.main.data.Restaurant

data class MainViewState(
    val nearestRestaurant: List<Restaurant> = emptyList(),
    val popularRestaurant: List<Restaurant> = emptyList(),
    val searchQuery: String = "",
)