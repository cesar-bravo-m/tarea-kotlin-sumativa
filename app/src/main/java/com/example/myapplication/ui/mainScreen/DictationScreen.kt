package com.example.myapplication.ui.mainScreen

import Microphone
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.app.Activity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import com.example.myapplication.ui.theme.AccesibleColors
import com.example.myapplication.ui.theme.AccessibleTypography

@Composable
fun DictationScreen(
    sttController: SpeechToTextController?,
    recognizedText: String,
    onRecognizedTextConsumed: () -> Unit,
    onNavigateBack: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    var speechHistory by remember { mutableStateOf(listOf<String>()) }
    val context = LocalContext.current
    val activity = context as Activity
    val isListening by sttController?.isListening?.collectAsState() ?: remember { mutableStateOf(false) }

    LaunchedEffect(recognizedText) {
        if (recognizedText.isNotEmpty()) {
            speechHistory = speechHistory + recognizedText
            onRecognizedTextConsumed()
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
                    tint = AccesibleColors.Secondary
                )
            }
            Text(
                "Dictar texto",
                style = AccessibleTypography().titleLarge,
                color = AccesibleColors.Secondary
            )
            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Texto dictado", style = AccessibleTypography().bodyLarge) },
            textStyle = AccessibleTypography().bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AccesibleColors.Secondary,
                unfocusedBorderColor = AccesibleColors.OnBackground,
                focusedLabelColor = AccesibleColors.Secondary,
                unfocusedLabelColor = AccesibleColors.OnBackground
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { 
                if (!isListening) {
                    sttController?.startListening(activity) { }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isListening) AccesibleColors.Primary else AccesibleColors.Secondary,
                contentColor = AccesibleColors.OnPrimary
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Microphone,
                    contentDescription = if (isListening) "Detener dictado" else "Iniciar dictado",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    if (isListening) "Detener" else "Dictar",
                    style = AccessibleTypography().labelLarge
                )
            }
        }

        if (isListening) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AccesibleColors.Secondary.copy(alpha = 0.1f)
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
                        color = AccesibleColors.Secondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Escuchando...",
                        style = AccessibleTypography().bodyLarge,
                        color = AccesibleColors.Secondary
                    )
                }
            }
        }

        if (speechHistory.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AccesibleColors.Secondary.copy(alpha = 0.05f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Historial de dictado",
                        style = AccessibleTypography().titleLarge,
                        color = AccesibleColors.Secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 200.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(speechHistory.reversed()) { speech ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = speech,
                                    style = AccessibleTypography().bodyMedium,
                                    color = AccesibleColors.OnBackground,
                                    modifier = Modifier.weight(1f)
                                )
                                IconButton(
                                    onClick = { 
                                        text = if (text.isBlank()) speech else "$text $speech"
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Add,
                                        contentDescription = "Agregar al texto",
                                        tint = AccesibleColors.Secondary
                                    )
                                }
                            }
                            Divider(color = AccesibleColors.Secondary.copy(alpha = 0.1f))
                        }
                    }
                }
            }
        }
    }
} 