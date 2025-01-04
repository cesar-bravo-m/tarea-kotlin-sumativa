package com.example.myapplication.ui.theme

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp

object ThemeController {
    var fontScale by mutableFloatStateOf(1f)
    
    fun increaseFontSize() {
        if (fontScale < 2.0f) {
            fontScale += 0.1f
        }
    }
    
    fun decreaseFontSize() {
        if (fontScale > 1.0f) {
            fontScale -= 0.1f
        }
    }
    
    fun getScaledSp(originalSp: Int) = (originalSp * fontScale).sp
} 