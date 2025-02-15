package com.example.myapplication.ui.mainScreen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class SpeechToTextController(private val context: Context) {
    private val _isListening = MutableStateFlow(false)
    val isListening = _isListening.asStateFlow()

    fun startListening(activity: Activity, onResult: (String) -> Unit) {
        when {
            // Check if permission is already granted
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {
                startSpeechRecognition(activity)
            }
            // Show rationale if needed
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.RECORD_AUDIO
            ) -> {
                Toast.makeText(
                    context,
                    "Se necesita permiso para acceder al micrófono",
                    Toast.LENGTH_LONG
                ).show()
                requestAudioPermission(activity)
            }
            // Request permission
            else -> {
                requestAudioPermission(activity)
            }
        }
    }

    private fun startSpeechRecognition(activity: Activity) {
        try {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-CL")
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Hablando...")
            }

            _isListening.value = true
            activity.startActivityForResult(intent, SPEECH_REQUEST_CODE)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "El reconocimiento de voz no está disponible en este dispositivo",
                Toast.LENGTH_SHORT
            ).show()
            _isListening.value = false
        }
    }

    private fun requestAudioPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            PERMISSION_REQUEST_CODE
        )
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        activity: Activity
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    startSpeechRecognition(activity)
                } else {
                    Toast.makeText(
                        context,
                        "Se necesita permiso para usar el reconocimiento de voz",
                        Toast.LENGTH_SHORT
                    ).show()
                    _isListening.value = false
                }
                return
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, onResult: (String) -> Unit) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = results?.get(0) ?: ""
            onResult(spokenText)
        }
        _isListening.value = false
    }

    companion object {
        private const val SPEECH_REQUEST_CODE = 0
        private const val PERMISSION_REQUEST_CODE = 1
    }
} 