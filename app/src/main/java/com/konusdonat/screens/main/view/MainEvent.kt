package com.konusdonat.screens.main.view

sealed class MainEvent {
    data class Query(val value: String) : MainEvent()
}