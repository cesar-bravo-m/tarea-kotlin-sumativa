import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AccesibleColors
import com.example.myapplication.ui.theme.AccessibleTypography
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import com.example.myapplication.ui.mainScreen.TextToSpeechController

@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("") }
    var isListening by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val ttsController = remember { TextToSpeechController(context) }
    val isSpeaking by ttsController.isSpeaking.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            ttsController.shutdown()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "YourVoice",
            style = AccessibleTypography().headlineLarge,
            color = AccesibleColors.Primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Text input area
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Escribe o dicta tu mensaje", style = AccessibleTypography().bodyLarge) },
            textStyle = AccessibleTypography().bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AccesibleColors.Primary,
                unfocusedBorderColor = AccesibleColors.OnBackground,
                focusedLabelColor = AccesibleColors.Primary,
                unfocusedLabelColor = AccesibleColors.OnBackground
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Text to Speech button
            Button(
                onClick = { ttsController.speak(text) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSpeaking) AccesibleColors.Secondary else AccesibleColors.Primary,
                    contentColor = AccesibleColors.OnPrimary
                ),
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowUp,
                        contentDescription = if (isSpeaking) "Detener lectura" else "Leer en voz alta",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        if (isSpeaking) "Detener" else "Leer en voz alta",
                        style = AccessibleTypography().labelLarge
                    )
                }
            }

            // Speech to Text button
            Button(
                onClick = { isListening = !isListening },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isListening) AccesibleColors.Secondary else AccesibleColors.Primary,
                    contentColor = AccesibleColors.OnPrimary
                ),
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Call,
                        contentDescription = if (isListening) "Detener dictado" else "Iniciar dictado",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        if (isListening) "Detener" else "Dictar",
                        style = AccessibleTypography().labelLarge
                    )
                }
            }
        }

        if (isSpeaking) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AccesibleColors.Primary.copy(alpha = 0.1f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        color = AccesibleColors.Primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Leyendo...",
                        style = AccessibleTypography().bodyLarge,
                        color = AccesibleColors.Primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Help text
        Text(
            text = "Usa los botones para dictar texto o escuchar el mensaje",
            style = AccessibleTypography().bodyMedium,
            color = AccesibleColors.OnBackground.copy(alpha = 0.7f)
        )
    }
}
