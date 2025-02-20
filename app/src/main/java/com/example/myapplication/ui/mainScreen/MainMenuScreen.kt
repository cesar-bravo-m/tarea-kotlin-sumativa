package com.example.myapplication.ui.mainScreen

import Microphone
import Text_to_speech
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AccesibleColors
import com.example.myapplication.ui.theme.AccessibleTypography

@Composable
fun MainMenuScreen(
    onNavigateToReader: () -> Unit,
    onNavigateToDictation: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "YourVoice",
            style = AccessibleTypography().headlineLarge,
            color = AccesibleColors.Primary,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Button(
            onClick = onNavigateToReader,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 32.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AccesibleColors.Primary,
                contentColor = AccesibleColors.OnPrimary
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Text_to_speech,
                    contentDescription = "Leer en voz alta",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Leer en voz alta",
                    style = AccessibleTypography().titleLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToDictation,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 32.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AccesibleColors.Secondary,
                contentColor = AccesibleColors.OnPrimary
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Microphone,
                    contentDescription = "Dictar texto",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Dictar texto",
                    style = AccessibleTypography().titleLarge
                )
            }
        }
    }
} 