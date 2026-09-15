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
fun RegisterScreen(
    onBack: () -> Unit,
    onRegister: (fullName: String, email: String, phone: String, pin: String, onResult: (AuthResult) -> Unit) -> Unit,
    onRegistered: () -> Unit,
    modifier: Modifier = Modifier
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier.fillMaxWidth()) {
        ACITopBar(title = "Register", onNavClick = onBack)

        LazyColumn(contentPadding = PaddingValues(bottom = ACISpacing.xxl)) {
            item {
                ACISectionHeader(
                    title = "Create your account",
                    subtitle = "Just a few basic details — used to sign you back in on this device."
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
                            value = fullName,
                            onValueChange = { fullName = it; error = null },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Full name") },
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(ACISpacing.sm))
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
                            value = phone,
                            onValueChange = { phone = it; error = null },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Phone (optional)") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                        )
                        Spacer(modifier = Modifier.height(ACISpacing.sm))
                        OutlinedTextField(
                            value = pin,
                            onValueChange = { pin = it; error = null },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Choose a PIN") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                        )
                        Spacer(modifier = Modifier.height(ACISpacing.sm))
                        OutlinedTextField(
                            value = confirmPin,
                            onValueChange = { confirmPin = it; error = null },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Confirm PIN") },
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
                            text = "Register",
                            onClick = {
                                if (pin != confirmPin) {
                                    error = "PINs don't match."
                                } else if (pin.length < 4) {
                                    error = "PIN must be at least 4 digits."
                                } else {
                                    onRegister(fullName, email, phone, pin) { result ->
                                        when (result) {
                                            AuthResult.Success -> onRegistered()
                                            AuthResult.EmailAlreadyRegistered -> error = "That email is already registered."
                                            AuthResult.InvalidCredentials -> error = "Something went wrong. Try again."
                                        }
                                    }
                                }
                            },
                            type = ACIButtonType.Primary,
                            enabled = fullName.isNotBlank() && email.isNotBlank() && pin.isNotBlank(),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    ACITheme {
        RegisterScreen(onBack = {}, onRegister = { _, _, _, _, _ -> }, onRegistered = {})
    }
}
