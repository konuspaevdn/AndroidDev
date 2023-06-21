package com.konusdonat.screens.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.konusdonat.R
import com.konusdonat.screens.signup.view.RegistrationEvent
import com.konusdonat.screens.signup.view.RegistrationViewModel

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.Registration(navController: NavController, registrationViewModel: RegistrationViewModel) {
    val viewState by registrationViewModel.viewState.collectAsState()
        Text(
            text = stringResource(R.string.create_your_account),
            color = Color.White,
            fontSize = 28.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        )
        TextField(
            value = viewState.email,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 12.dp)
                .fillMaxWidth(0.8f), singleLine = true,
            onValueChange = { registrationViewModel.obtainEvent(RegistrationEvent.EmailChange(it)) },
            label = { Text( text = viewState.emailLabel ) }
        )
        TextField(
            value = viewState.passwordStars,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 12.dp)
                .fillMaxWidth(0.8f),
            singleLine = true,
            onValueChange = { registrationViewModel.obtainEvent(RegistrationEvent.PasswordChange(it)) },
            label = { Text( text = viewState.passwordLabel ) },
            trailingIcon = { Icon(imageVector = Icons.Default.Info,
                contentDescription = stringResource(R.string.sign_up_hint),
                modifier = Modifier.clickable { registrationViewModel.obtainEvent(RegistrationEvent.HintClick) }
            ) }
        )
        TextField(
            value = viewState.passwordAgainStars,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 12.dp)
                .fillMaxWidth(0.8f),
            singleLine = true,
            onValueChange = { registrationViewModel.obtainEvent(
                RegistrationEvent.PasswordRepeatedChange(
                    it
                )
            ) },
            label = { Text( text = viewState.passwordAgainLabel ) },
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
            ) {
            Image(painter = painterResource(id = viewState.imageId),
                contentDescription = stringResource(R.string.agreement_to_email),
                modifier = Modifier
                    .size(28.dp)
                    .clickable { registrationViewModel.obtainEvent(RegistrationEvent.CheckClick) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = R.string.agreement_to_email),
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                registrationViewModel.obtainEvent(RegistrationEvent.SignUpButtonClick)
                      },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .align(alignment = Alignment.CenterHorizontally)
                .height(60.dp)
        ) {
            Text(stringResource(id = R.string.signup), fontSize = 28.sp)
        }
        Text (
            text = viewState.verdict,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth(0.9f)
                .clickable { navController.navigate("main") },
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
}