package com.example.myapplication.ui.theme

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ThemeControllerTest {

    @Before
    fun setUp() {
        ThemeController.fontScale = 1f
    }

    @Test
    fun `el tamaño de fuente debe incrementar en 0_1 cuando está por debajo del máximo`() {
        assertEquals(1f, ThemeController.fontScale)
        ThemeController.increaseFontSize()
        assertEquals(1.1f, ThemeController.fontScale, 0.001f)
    }

    @Test
    fun `el tamaño de fuente debe disminuir en 0_1 cuando está por encima del mínimo`() {
        ThemeController.fontScale = 1.5f
        ThemeController.decreaseFontSize()
        assertEquals(1.4f, ThemeController.fontScale, 0.001f)
    }

    @Test
    fun `getScaledSp debe retornar el valor sp escalado`() {
        ThemeController.fontScale = 1.5f
        val result = ThemeController.getScaledSp(16)
        assertEquals(24f, result.value, 0.001f)
    }
} 