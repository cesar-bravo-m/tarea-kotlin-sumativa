package com.example.myapplication.ui.session

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserManagerTest {

    @Before
    fun setUp() {
        users.clear()
    }

    @Test
    fun `autenticación debe fallar con credenciales inválidas`() {
        val email = "test@example.com"
        UserManager.register("", email, "Test123!")
        val result = UserManager.authenticate(email, "WrongPass123!")
        assertFalse(result.success)
    }

    @Test
    fun `verificación de email debe ser exitosa para email existente`() {
        val email = "test@example.com"
        UserManager.register("", email, "Test123!")
        val result = UserManager.verifyEmail(email)
        assertTrue(result)
    }

    @Test
    fun `verificación de email debe fallar para email no existente`() {
        val result = UserManager.verifyEmail("nonexistent@example.com")
        assertFalse(result)
    }

    @Test
    fun `cambio de contraseña debe actualizar la contraseña para usuario existente`() {
        val email = "test@example.com"
        val oldPassword = "Test123!"
        val newPassword = "NewTest123!"
        UserManager.register("", email, oldPassword)
        val result = UserManager.resetPassword(email, newPassword)
        assertTrue(result)
        assertTrue(UserManager.authenticate(email, newPassword).success)
        assertFalse(UserManager.authenticate(email, oldPassword).success)
    }

    @Test
    fun `cambio de contraseña debe fallar para usuario no existente`() {
        val result = UserManager.resetPassword("noexite@example.com", "NewPass123!")
        assertFalse(result)
    }
} 