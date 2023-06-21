package com.konusdonat.screens.signup.view

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.konusdonat.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegistrationViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(RegistrationState())
    val viewState: StateFlow<RegistrationState> = _viewState

    fun obtainEvent(event: RegistrationEvent) {
        when(event) {
            is RegistrationEvent.EmailChange -> changeEmail(event.value)
            is RegistrationEvent.PasswordChange -> changePassword(event.value)
            is RegistrationEvent.PasswordRepeatedChange -> changeRepeatedPassword(event.value)
            RegistrationEvent.HintClick -> showHint()
            RegistrationEvent.CheckClick -> toggleCheck()
            RegistrationEvent.SignUpButtonClick -> verify()
            else -> {}
        }
    }

    private fun changeEmail(value: String) {
        _viewState.value = _viewState.value.copy(email = value,
            emailLabel = "Email"
        )
    }

    private fun changePassword(value: String) {
        _viewState.value = _viewState.value.copy(password = value,
            passwordStars = String(CharArray(value.length) { return@CharArray '*' }),
            passwordLabel = "Password"
        )
    }

    private fun changeRepeatedPassword(value: String) {
        _viewState.value = _viewState.value.copy(passwordAgain = value,
            passwordAgainStars = String(CharArray(value.length) { return@CharArray '*' }),
            passwordAgainLabel = "Password again"
        )
    }

    private fun showHint() {
        _viewState.value = _viewState.value.copy(passwordLabel =
        "Password should be at least 8 characters long")
    }

    private fun toggleCheck() {
        var newId = if (_viewState.value.check) R.drawable.empty_circle else R.drawable.checked_circle
        _viewState.value = _viewState.value.copy(check = !_viewState.value.check,
        imageId = newId)
    }

    private fun verify() {
        val fail: String = "Oops, there are some errors. Make sure you have filled everything correctly"
        val success: String = "If you input a correct email address, you should receive a confirmation email"
        var fault: Boolean = false

        if (_viewState.value.email.isBlank()) {
            _viewState.value = _viewState.value.copy(emailLabel = "No email put")
            fault = true
        }

        if (_viewState.value.password.isBlank()) {
            _viewState.value = _viewState.value.copy(passwordLabel = "No password put")
            fault = true
        }
        else if (_viewState.value.password.length < 8) {
            _viewState.value = _viewState.value.copy(passwordLabel =
            "Password should be at least 8 characters long")
            fault = true
        }

        if (_viewState.value.password != _viewState.value.passwordAgain) {
            _viewState.value = _viewState.value.copy(passwordAgainLabel = "Repeated password mismatch")
            fault = true
        }

        if (fault) {
            _viewState.value = _viewState.value.copy(verdict = fail)
        } else {
            _viewState.value = _viewState.value.copy(verdict = success)
        }
    }
}