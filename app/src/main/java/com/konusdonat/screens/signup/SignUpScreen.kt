package com.konusdonat.screens.signup

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.konusdonat.R
import com.konusdonat.screens.signup.view.RegistrationViewModel
import com.konusdonat.ui.theme.MyApplicationTheme

@Composable
fun SignUpScreen(navController: NavController, registrationViewModel: RegistrationViewModel)
{
    val background = painterResource(id = R.drawable.signupwallpaper)

    Box {
        Image(
            painter = background,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {
            Logo()
            Registration(navController, registrationViewModel)
        }
    }

}

@Preview
@Composable
fun SignUpPreview()
{
    MyApplicationTheme() {
        //SignUpScreen(NavHostController)
    }
}