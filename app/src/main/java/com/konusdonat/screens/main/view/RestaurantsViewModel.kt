package com.konusdonat.screens.main.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konusdonat.screens.main.data.Restaurant
import com.konusdonat.screens.main.db.RestaurantRepository
import com.konusdonat.screens.main.data.RemoteRestaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository) : ViewModel() {

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData(MainViewState())
    val viewState: LiveData<MainViewState> = _viewState

    init {
        fetchRestaurants()
    }

    fun RemoteRestaurant.mapToRestaurant(): Restaurant {
        return Restaurant(name = name, deliveryTime = deliveryTime, logo = image)
    }


    private fun fetchRestaurants() {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.fetchCatalog()
                .collectLatest { response ->
                    _viewState.postValue(
                        _viewState.value?.copy(
                            nearestRestaurant = response.nearest.map { it.mapToRestaurant() },
                            popularRestaurant = response.popular.map { it.mapToRestaurant() }
                        )
                    )
                }
        }
    }

    fun obtainEvent(event: MainEvent) {
        when(event) {
            is MainEvent.Query -> searchQuery(event.value)
            else -> {}
        }
    }

    fun searchQuery(query: String) {
        _viewState.postValue(_viewState.value?.copy(searchQuery = query))
    }
}