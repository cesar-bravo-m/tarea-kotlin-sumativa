package com.example.myapplication.ui.session

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.text.input.VisualTransformation
import com.example.myapplication.ui.theme.AccesibleColors
import com.example.myapplication.ui.theme.AccessibleTypography

@Composable
fun LoginScreen(
    innerPadding: PaddingValues,
    setShowRecoveryDialog: (Boolean) -> Unit,
    setRecoveryStep: (Int) -> Unit,
    setEmail: (String) -> Unit,
    setVerificationCode: (String) -> Unit,
    setIsLoggedIn: (Boolean) -> Unit,
    setShowRegister: (Boolean) -> Unit,
    context: Context
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "YourVoice",
            style = AccessibleTypography().headlineLarge,
            color = AccesibleColors.Primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", style = AccessibleTypography().bodyLarge) },
            textStyle = AccessibleTypography().bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AccesibleColors.Primary,
                unfocusedBorderColor = AccesibleColors.OnBackground,
                focusedLabelColor = AccesibleColors.Primary,
                unfocusedLabelColor = AccesibleColors.OnBackground
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", style = AccessibleTypography().bodyLarge) },
            textStyle = AccessibleTypography().bodyLarge,
            visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Lock else Icons.Default.Lock,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                        tint = AccesibleColors.Primary
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AccesibleColors.Primary,
                unfocusedBorderColor = AccesibleColors.OnBackground,
                focusedLabelColor = AccesibleColors.Primary,
                unfocusedLabelColor = AccesibleColors.OnBackground
            )
        )
        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                
                if (UserManager.authenticate(email, password)) {
                    Toast.makeText(context, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()
                    setIsLoggedIn(true)
                } else {
                    Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AccesibleColors.Primary,
                contentColor = AccesibleColors.OnPrimary
            )
        ) {
            Text("Iniciar sesión", style = AccessibleTypography().labelLarge)
        }

        TextButton(
            onClick = { setShowRegister(true) },
            colors = ButtonDefaults.textButtonColors(
                contentColor = AccesibleColors.Primary
            )
        ) {
            Text("¿No tienes cuenta? Regístrate", 
                style = AccessibleTypography().bodyLarge)
        }

        TextButton(
            onClick = {
                setShowRecoveryDialog(true)
                setRecoveryStep(1)
                setEmail("")
                setVerificationCode("")
            },
            colors = ButtonDefaults.textButtonColors(
                contentColor = AccesibleColors.Primary
            )
        ) {
            Text("Recuperar contraseña",
                style= AccessibleTypography().bodyMedium)
        }
    }
}