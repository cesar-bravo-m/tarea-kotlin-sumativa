package com.example.myapplication.ui.session

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.ui.theme.AccesibleColors
import com.example.myapplication.ui.theme.AccessibleTypography

@Composable
fun RecoveryDialog(
    setShowRecoveryDialog: (Boolean) -> Unit,
    setRecoveryStep: (Int) -> Unit,
    recoveryStep: Int,
    email: String,
    setEmail: (String) -> Unit,
    verificationCode: String,
    setVerificationCode: (String) -> Unit,
    context: Context
) {
    val (newPassword, setNewPassword) = remember { mutableStateOf("") }

    Dialog(onDismissRequest = {
        setShowRecoveryDialog(false)
        setRecoveryStep(1)
    }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = AccesibleColors.Surface
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (recoveryStep == 1) "Recuperar Contraseña" else "Verificar Código",
                    style = AccessibleTypography.titleLarge,
                    color = AccesibleColors.Primary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                if (recoveryStep == 1) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { setEmail(it) },
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    Button(
                        onClick = {
                            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                Toast.makeText(context, "Email inválido", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (UserManager.verifyEmail(email)) {
                                setRecoveryStep(2)
                                Toast.makeText(
                                    context,
                                    "Código enviado a su email",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Email no encontrado",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Enviar Código")
                    }
                } else {
                    OutlinedTextField(
                        value = verificationCode,
                        onValueChange = { setVerificationCode(it) },
                        label = { Text("Código de Verificación") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { setNewPassword(it) },
                        label = { Text("Nueva Contraseña") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    Button(
                        onClick = {
                            if (verificationCode == "000") {
                                if (UserManager.resetPassword(email, newPassword)) {
                                    Toast.makeText(
                                        context,
                                        "Contraseña actualizada exitosamente",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    setShowRecoveryDialog(false)
                                    setRecoveryStep(1)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error al actualizar la contraseña",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Código inválido. Use: 000",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Verificar y Cambiar Contraseña")
                    }
                }

                TextButton(
                    onClick = {
                        setShowRecoveryDialog(false)
                        setRecoveryStep(1)
                    }
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}
