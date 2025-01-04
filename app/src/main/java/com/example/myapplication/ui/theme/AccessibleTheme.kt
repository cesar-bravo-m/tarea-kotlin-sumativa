package com.example.myapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable

public object AccesibleColors {
    val Primary = Color(0xFF0052CC)
    val Secondary = Color(0xFFA32F2F)
    val OnPrimary = Color.White
    val Background = Color.White
    val OnBackground = Color(0xFF1A1A1A)
    val Error = Color(0xFFD32F2F)
    val Surface = Color.White
    val OnSurface = Color(0xFF1A1A1A)
}

@Composable
public fun AccessibleTypography() = Typography(
    headlineLarge = TextStyle(
        fontSize = ThemeController.getScaledSp(32),
        fontWeight = FontWeight.Bold,
        lineHeight = ThemeController.getScaledSp(40),
    ),
    headlineMedium = TextStyle(
        fontSize = ThemeController.getScaledSp(28),
        fontWeight = FontWeight.Bold,
        lineHeight = ThemeController.getScaledSp(36),
    ),
    titleLarge = TextStyle(
        fontSize = ThemeController.getScaledSp(24),
        fontWeight = FontWeight.Medium,
        lineHeight = ThemeController.getScaledSp(32),
    ),
    bodyLarge = TextStyle(
        fontSize = ThemeController.getScaledSp(18),
        lineHeight = ThemeController.getScaledSp(26),
    ),
    bodyMedium = TextStyle(
        fontSize = ThemeController.getScaledSp(16),
        lineHeight = ThemeController.getScaledSp(24),
    ),
    labelLarge = TextStyle(
        fontSize = ThemeController.getScaledSp(16),
        fontWeight = FontWeight.Medium,
        lineHeight = ThemeController.getScaledSp(24),
    )
) 