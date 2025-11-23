package com.nickolss.madeforyou.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    // Verifica se já tem usuário logado ao abrir o app
    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    // Login
    suspend fun login(email: String, pass: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
            if (result.user != null) {
                Result.success(result.user!!)
            } else {
                Result.failure(Exception("Erro ao obter usuário"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Registro
    suspend fun register(email: String, pass: String, name: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, pass).await()
            val user = result.user

            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("Falha ao criar usuário"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Logout
    fun logout() {
        firebaseAuth.signOut()
    }
}
