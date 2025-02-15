package com.example.myapplication.ui.mainScreen

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import java.util.Locale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TextToSpeechController(private val context: Context) : TextToSpeech.OnInitListener {
    private var textToSpeech: TextToSpeech? = null
    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking = _isSpeaking.asStateFlow()

    init {
        textToSpeech = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech?.setLanguage(Locale("es", "ES"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context, "Error: Idioma no soportado", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Error al inicializar Text to Speech", Toast.LENGTH_SHORT).show()
        }
    }

    fun speak(text: String) {
        if (text.isBlank()) {
            Toast.makeText(context, "Por favor, ingrese texto para leer", Toast.LENGTH_SHORT).show()
            return
        }

        textToSpeech?.let { tts ->
            if (tts.isSpeaking) {
                stop()
                return
            }
            
            _isSpeaking.value = true
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            
            Thread {
                while (tts.isSpeaking) {
                    Thread.sleep(100)
                }
                _isSpeaking.value = false
            }.start()
        }
    }

    fun stop() {
        textToSpeech?.stop()
        _isSpeaking.value = false
    }

    fun shutdown() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
} 