package com.konusdonat.screens.signup.view

import com.konusdonat.R

data class RegistrationState (
    val email: String = "",
    val emailLabel: String = "Email",

    val password: String = "",
    val passwordStars: String = "",
    val passwordLabel: String = "Password",

    val passwordAgain: String = "",
    val passwordAgainStars: String = "",
    val passwordAgainLabel: String = "Password again",

    val check: Boolean = false,
    val imageId: Int = R.drawable.empty_circle,

    val verdict: String = ""
)
