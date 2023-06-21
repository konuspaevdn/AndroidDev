package com.konusdonat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.konusdonat.screens.main.MainScreen
import com.konusdonat.screens.main.view.MainViewModel
import com.konusdonat.screens.signup.SignUpScreen
import com.konusdonat.screens.signup.view.RegistrationViewModel
import com.konusdonat.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var isDarkTheme by remember { mutableStateOf(false) }
            MyApplicationTheme(darkTheme = isDarkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "signup") {
                        composable("signup") {
                            val registrationViewModel = hiltViewModel<RegistrationViewModel>()
                            SignUpScreen(navController = navController, registrationViewModel = registrationViewModel)
                        }
                        composable("main") {
                            val mainViewModel = hiltViewModel<MainViewModel>()
                            MainScreen(mainViewModel = mainViewModel)
                        }
                    }
                }

            }
        }
    }
}
