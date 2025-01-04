package com.example.myapplication.ui.session

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun UIManager() {
    val (showRecoveryDialog, setShowRecoveryDialog) = androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(
            false
        )
    }
    val (showRegister, setShowRegister) = androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(
            false
        )
    }
    val (recoveryStep, setRecoveryStep) = androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableIntStateOf(
            1
        )
    }
    val (email, setEmail) = androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }
    val (verificationCode, setVerificationCode) = androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(
            ""
        )
    }
    val (isLoggedIn, setIsLoggedIn) = androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(
            false
        )
    }
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