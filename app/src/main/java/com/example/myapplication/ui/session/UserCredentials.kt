package com.example.myapplication.ui.session

data class User(
    var password: String,
    val email: String
)

object UserManager {
    // REQUERIMIENTO: "deberá contemplar un array que almacene los datos de 5 usuarios con sus
    //                 respectivas contraseñas"
    private val users = mutableListOf<User>()

    init {
        users.add(User("admin@example.com" , "admin"))
        users.add(User("cesa.bravo@duocuc.cl" , "admin"))
        users.add(User("pablo" , "vilches"))
    }

    fun authenticate(email: String, password: String): Boolean {
        return users.any { it.email == email && it.password == password }
    }

    fun register(email: String, password: String): Boolean {
        if (users.any { it.email == email }) {
            return false
        }
        users.add(User(email, password))
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