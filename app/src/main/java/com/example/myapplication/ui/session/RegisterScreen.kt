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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AccesibleColors
import com.example.myapplication.ui.theme.AccessibleTypography

@Composable
fun RegisterScreen(
    innerPadding: PaddingValues,
    setIsLoggedIn: (Boolean) -> Unit,
    setShowRegister: (Boolean) -> Unit,
    context: Context
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Crear cuenta",
            style = AccessibleTypography().headlineLarge,
            color = AccesibleColors.Primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nombre", style = AccessibleTypography().bodyLarge) },
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
                        imageVector = if (confirmPasswordVisible) Icons.Default.Lock else Icons.Default.Lock,
                        contentDescription = if (confirmPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
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

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Contraseña", style = AccessibleTypography().bodyLarge) },
            textStyle = AccessibleTypography().bodyLarge,
            visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (confirmPasswordVisible) Icons.Default.Lock else Icons.Default.Lock,
                        contentDescription = if (confirmPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
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
                if (fullName.isBlank() || fullName.isBlank()) {
                    Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                
                // Validar formato de email
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(context, "Por favor, ingrese un email válido", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                
                // Validar contraseña
                if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}\$".toRegex())) {
                    Toast.makeText(
                        context, 
                        "La contraseña debe tener al menos 6 caracteres, una mayúscula, una minúscula, un número y un carácter especial",
                        Toast.LENGTH_LONG
                    ).show()
                    return@Button
                }
                
                if (password != confirmPassword) {
                    Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                
                if (UserManager.register(fullName, email, password)) {
                    Toast.makeText(context, "¡Cuenta creada exitosamente!", Toast.LENGTH_SHORT).show()
                    setShowRegister(false)
                } else {
                    Toast.makeText(context, "Este email ya está registrado", Toast.LENGTH_SHORT).show()
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
            Text("Crear cuenta", style = AccessibleTypography().labelLarge)
        }

        TextButton(
            onClick = { setShowRegister(false) }
        ) {
            Text("¿Ya tienes una cuenta? Inicia sesión",
                style= AccessibleTypography().bodyMedium)
        }
    }
} 