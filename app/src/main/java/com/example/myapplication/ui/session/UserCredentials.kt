package com.example.myapplication.ui.session

data class User(
    val email: String,
    var password: String,
)

object UserManager {
    // REQUERIMIENTO: "deberá contemplar un array que almacene los datos de 5 usuarios con sus
    //                 respectivas contraseñas"
    private var users = arrayOf(
        User("admin@example.com" , "admin"),
        User("cesa.bravo@duocuc.cl" , "admin"),
        User("cesar@gmail.com" , "admin"),
        User("antonio@gmail.com" , ": admin"),
        User("ignacio@gmail.com" , ": admin"),
    )

    init {
    }

    fun authenticate(email: String, password: String): Boolean {
        return users.any { it.email == email && it.password == password }
    }

    fun register(email: String, password: String): Boolean {
        if (users.any { it.email == email }) {
            return false
        }
        users = users.plus(User(email, password))
        return true
    }

    fun resetPassword(email: String, newPassword: String): Boolean {
        val user = users.find { it.email == email }
        if (user != null) {
            user.password = newPassword
            return true
        }
        return false
    }

    fun verifyEmail(email: String): Boolean {
        return users.any { it.email == email }
    }
}