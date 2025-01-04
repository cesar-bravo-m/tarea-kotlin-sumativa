package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AccesibleColors
import com.example.myapplication.ui.theme.ThemeController

@Composable
fun FontSizeControls() {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        FloatingActionButton(
            onClick = { ThemeController.decreaseFontSize() },
            containerColor = AccesibleColors.Secondary,
            contentColor = AccesibleColors.OnPrimary,
            shape = CircleShape,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = "Aumentar tamaño de letra",
                modifier = Modifier.size(32.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        FloatingActionButton(
            onClick = { ThemeController.increaseFontSize() },
            containerColor = AccesibleColors.Primary,
            contentColor = AccesibleColors.OnPrimary,
            shape = CircleShape,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                Icons.Default.KeyboardArrowUp,
                contentDescription = "Aumentar tamaño de letra",
                modifier = Modifier.size(32.dp)
            )
        }
    }
} 