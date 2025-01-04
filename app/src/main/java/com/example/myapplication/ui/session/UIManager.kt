package com.example.myapplication.ui.session

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.FontSizeControls
import androidx.compose.foundation.layout.Box

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

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = { FontSizeControls() }
        ) { innerPadding ->
            if (isLoggedIn) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "En construcción")
                    Button(onClick = { setIsLoggedIn(false) }) {
                        Text(text = "Volver")
                    }
                }
            } else if (showRegister) {
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
}