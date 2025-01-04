package com.example.myapplication.ui.session

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

val users = mutableListOf<User>()

@Composable
fun UIManager() {
    val (showRecoveryDialog, setShowRecoveryDialog) = remember { mutableStateOf(false) }
    val (showRegister, setShowRegister) = remember { mutableStateOf(false) }
    val (recoveryStep, setRecoveryStep) = remember { mutableIntStateOf(1) }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (verificationCode, setVerificationCode) = remember { mutableStateOf("") }
    val (isLoggedIn, setIsLoggedIn) = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (showRegister) {
            RegisterScreen(
                innerPadding,
                setIsLoggedIn,
                setShowRegister,
                context
            )
        } else if (!isLoggedIn && !showRecoveryDialog) {
            LoginScreen(
                innerPadding,
                setShowRecoveryDialog,
                setRecoveryStep,
                setEmail,
                setVerificationCode,
                setIsLoggedIn,
                setShowRegister,
                context
            )
        } else if (!isLoggedIn && showRecoveryDialog) {
            RecoveryDialog(
                setShowRecoveryDialog,
                setRecoveryStep,
                recoveryStep,
                email,
                setEmail,
                verificationCode,
                setVerificationCode,
                context
            )
        }
    }
}