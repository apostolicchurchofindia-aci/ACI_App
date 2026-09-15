package com.aci.feature.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.aci.core.domain.repository.AuthResult
import com.aci.core.ui.components.ACIButton
import com.aci.core.ui.components.ACIButtonType
import com.aci.core.ui.components.ACICard
import com.aci.core.ui.components.ACISectionHeader
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.theme.ACISpacing
import com.aci.core.ui.theme.ACITheme

@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onLogin: (email: String, pin: String, onResult: (AuthResult) -> Unit) -> Unit,
    onLoggedIn: () -> Unit,
    onGoToRegister: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier.fillMaxWidth()) {
        ACITopBar(title = "Sign In", onNavClick = onBack)

        LazyColumn(contentPadding = PaddingValues(bottom = ACISpacing.xxl)) {
            item {
                ACISectionHeader(
                    title = "Sign In",
                    subtitle = "Sign in with the email and PIN you registered with."
                )
            }

            item {
                ACICard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ACISpacing.md, vertical = ACISpacing.xs)
                ) {
                    Column(modifier = Modifier.padding(ACISpacing.md)) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it; error = null },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Email") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )
                        Spacer(modifier = Modifier.height(ACISpacing.sm))
                        OutlinedTextField(
                            value = pin,
                            onValueChange = { pin = it; error = null },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("PIN") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                        )
                        if (error != null) {
                            Spacer(modifier = Modifier.height(ACISpacing.xs))
                            Text(
                                text = error!!,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        Spacer(modifier = Modifier.height(ACISpacing.md))
                        ACIButton(
                            text = "Sign In",
                            onClick = {
                                onLogin(email, pin) { result ->
                                    when (result) {
                                        AuthResult.Success -> onLoggedIn()
                                        AuthResult.InvalidCredentials -> error = "Email or PIN is incorrect."
                                        AuthResult.EmailAlreadyRegistered -> Unit
                                    }
                                }
                            },
                            type = ACIButtonType.Primary,
                            enabled = email.isNotBlank() && pin.isNotBlank(),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(ACISpacing.xs))
                        TextButton(onClick = onGoToRegister, modifier = Modifier.fillMaxWidth()) {
                            Text("New here? Register")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    ACITheme {
        LoginScreen(onBack = {}, onLogin = { _, _, onResult -> onResult(AuthResult.InvalidCredentials) }, onLoggedIn = {}, onGoToRegister = {})
    }
}
