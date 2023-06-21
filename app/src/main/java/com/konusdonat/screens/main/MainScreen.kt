package com.konusdonat.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.konusdonat.R
import com.konusdonat.screens.main.data.Restaurant
import com.konusdonat.screens.main.view.MainEvent
import com.konusdonat.screens.main.view.MainViewModel
import com.konusdonat.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, mainViewModel: MainViewModel) {
    val state by mainViewModel.viewState.observeAsState()
    val viewState = state ?: return
    val background = painterResource(id = R.drawable.signupwallpaper)
    Box {
        Image(
            painter = background,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column {
            Text(
                text = stringResource(R.string.search_for_a_restaurant),
                fontSize = 20.sp, color = Color.White
            )
            TextField(
                value = viewState.searchQuery,
                onValueChange = {mainViewModel.obtainEvent(MainEvent.Query(it))}
            )
            Text(
                text = "Nearest restaurants",
                fontSize = 20.sp, color = Color.White
            )
            LazyRow(
                content = {
                    viewState.nearestRestaurant.forEach {
                        item {
                            RestaurantCell(rest = it)
                        }
                    }
                }
            )
            Text(
                text = "Popular restaurants",
                fontSize = 20.sp, color = Color.White
            )
            LazyRow(
                content = {
                    viewState.popularRestaurant.forEach {
                        item {
                            RestaurantCell(rest = it)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun RestaurantCell(rest: Restaurant) {
    Card(modifier = Modifier.size(width = 140.dp, height = 188.dp)) {
        Column() {
            AsyncImage(
                model = rest.logo,
                contentDescription = "${rest.logo} logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .width(80.dp),
            )
            Text(
                text = rest.name
            )
            Text(
                text = "Delivary in: ${rest.deliveryTime}"
            )
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    MyApplicationTheme {
        //MainScreen()
    }
}