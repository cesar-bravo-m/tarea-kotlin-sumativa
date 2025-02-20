package com.example.myapplication.ui.mainScreen

import Text_to_speech
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AccesibleColors
import com.example.myapplication.ui.theme.AccessibleTypography

@Composable
fun ReaderScreen(onNavigateBack: () -> Unit) {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    val ttsController = remember { TextToSpeechController(context) }
    val isSpeaking by ttsController.isSpeaking.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            ttsController.shutdown()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = AccesibleColors.Primary
                )
            }
            Text(
                "Leer en voz alta",
                style = AccessibleTypography().titleLarge,
                color = AccesibleColors.Primary
            )
            Spacer(modifier = Modifier.width(48.dp)) // Para balance visual
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Escribe o pega el texto", style = AccessibleTypography().bodyLarge) },
            textStyle = AccessibleTypography().bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AccesibleColors.Primary,
                unfocusedBorderColor = AccesibleColors.OnBackground,
                focusedLabelColor = AccesibleColors.Primary,
                unfocusedLabelColor = AccesibleColors.OnBackground
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { ttsController.speak(text) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSpeaking) AccesibleColors.Secondary else AccesibleColors.Primary,
                contentColor = AccesibleColors.OnPrimary
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Text_to_speech,
                    contentDescription = if (isSpeaking) "Detener lectura" else "Leer texto",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    if (isSpeaking) "Detener" else "Leer",
                    style = AccessibleTypography().labelLarge
                )
            }
        }

        if (isSpeaking) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AccesibleColors.Primary.copy(alpha = 0.1f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        color = AccesibleColors.Primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Leyendo...",
                        style = AccessibleTypography().bodyLarge,
                        color = AccesibleColors.Primary
                    )
                }
            }
        }
    }
} 