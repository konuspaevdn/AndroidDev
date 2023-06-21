package com.konusdonat.screens.signup.view

import androidx.navigation.NavController

sealed class RegistrationEvent {
    data class EmailChange(val value: String) : RegistrationEvent()
    data class PasswordChange(val value: String) : RegistrationEvent()
    data class PasswordRepeatedChange(val value: String) : RegistrationEvent()
    object HintClick : RegistrationEvent()
    object CheckClick: RegistrationEvent()
    object SignUpButtonClick : RegistrationEvent()
}