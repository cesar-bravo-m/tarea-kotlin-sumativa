package com.example.myapplication.ui.session

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

data class User(
    val email: String,
    var password: String,
    val fullName: String = "",
    val avatar: Int = 0
)

object UserManager {
    private val db = Firebase.firestore
    private val users: List<User>
        get() = runBlocking {
            val snapshot = db.collection("usuarios").get().await()
            val resultado = mutableListOf<User>()

            for (document in snapshot) {
                val user = User(
                    email = document.getString("username") ?: "",
                    password = document.getString("password") ?: "",
                )
                resultado.add(user)
            }
            resultado
        }

    private var currentUser: User? = null

    fun authenticate(email: String, password: String): Boolean {
        val user = users.find { it.email == email && it.password == password }
        if (user != null) {
            currentUser = user
            return true
        }
        return false
    }

    fun register(email: String, password: String): Boolean {
        if (users.any { it.email == email }) {
            return false
        }
        val user = hashMapOf(
            "username" to email,
            "password" to password,
            "fullName" to "",
            "avatar" to 0
        )
        db.collection("usuarios")
            .add(user)
        return true
    }

    fun resetPassword(email: String, newPassword: String): Boolean {
        val user = users.find { it.email == email }
        if (user != null) {
            user.password = newPassword
            db.collection("usuarios").document(user.email).update("password", newPassword)
            return true
        }
        return false
    }

    fun verifyEmail(email: String): Boolean {
        return users.any { it.email == email }
    }
}