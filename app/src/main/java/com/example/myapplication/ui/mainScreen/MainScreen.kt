import androidx.compose.foundation.layout.*
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
import android.app.Activity
import com.example.myapplication.ui.mainScreen.SpeechToTextController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path

public val Text_to_speech: ImageVector
    get() {
        if (_Text_to_speech != null) {
            return _Text_to_speech!!
        }
        _Text_to_speech = ImageVector.Builder(
            name = "Text_to_speech",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(160f, 880f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(80f, 800f)
                verticalLineToRelative(-640f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(160f, 80f)
                horizontalLineToRelative(326f)
                lineToRelative(-80f, 80f)
                horizontalLineTo(160f)
                verticalLineToRelative(640f)
                horizontalLineToRelative(440f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(80f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(600f, 880f)
                close()
                moveToRelative(80f, -160f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(280f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(0f, -120f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(200f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(360f, 0f)
                lineTo(440f, 440f)
                horizontalLineTo(320f)
                verticalLineToRelative(-200f)
                horizontalLineToRelative(120f)
                lineToRelative(160f, -160f)
                close()
                moveToRelative(80f, -122f)
                verticalLineToRelative(-276f)
                quadToRelative(36f, 21f, 58f, 57f)
                reflectiveQuadToRelative(22f, 81f)
                reflectiveQuadToRelative(-22f, 81f)
                reflectiveQuadToRelative(-58f, 57f)
                moveToRelative(0f, 172f)
                verticalLineToRelative(-84f)
                quadToRelative(70f, -25f, 115f, -86.5f)
                reflectiveQuadTo(840f, 340f)
                reflectiveQuadToRelative(-45f, -139.5f)
                reflectiveQuadTo(680f, 114f)
                verticalLineToRelative(-84f)
                quadToRelative(104f, 27f, 172f, 112.5f)
                reflectiveQuadTo(920f, 340f)
                reflectiveQuadToRelative(-68f, 197.5f)
                reflectiveQuadTo(680f, 650f)
            }
        }.build()
        return _Text_to_speech!!
    }

private var _Text_to_speech: ImageVector? = null

public val Microphone: ImageVector
    get() {
        if (_Microphone != null) {
            return _Microphone!!
        }
        _Microphone = ImageVector.Builder(
            name = "Microphone",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(12f, 18.75f)
                curveTo(15.3137f, 18.75f, 18f, 16.0637f, 18f, 12.75f)
                verticalLineTo(11.25f)
                moveTo(12f, 18.75f)
                curveTo(8.6863f, 18.75f, 6f, 16.0637f, 6f, 12.75f)
                verticalLineTo(11.25f)
                moveTo(12f, 18.75f)
                verticalLineTo(22.5f)
                moveTo(8.25f, 22.5f)
                horizontalLineTo(15.75f)
                moveTo(12f, 15.75f)
                curveTo(10.3431f, 15.75f, 9f, 14.4069f, 9f, 12.75f)
                verticalLineTo(4.5f)
                curveTo(9f, 2.8432f, 10.3431f, 1.5f, 12f, 1.5f)
                curveTo(13.6569f, 1.5f, 15f, 2.8432f, 15f, 4.5f)
                verticalLineTo(12.75f)
                curveTo(15f, 14.4069f, 13.6569f, 15.75f, 12f, 15.75f)
                close()
            }
        }.build()
        return _Microphone!!
    }

private var _Microphone: ImageVector? = null


@Composable
fun MainScreen(
    sttController: SpeechToTextController? = null,
    recognizedText: String = "",
    onRecognizedTextConsumed: () -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var speechHistory by remember { mutableStateOf(listOf<String>()) }
    val context = LocalContext.current
    val activity = context as Activity
    val ttsController = remember { TextToSpeechController(context) }
    val isSpeaking by ttsController.isSpeaking.collectAsState()
    val isListening by sttController?.isListening?.collectAsState() ?: remember { mutableStateOf(false) }

    // Handle recognized text - now only adds to history
    LaunchedEffect(recognizedText) {
        if (recognizedText.isNotEmpty()) {
            speechHistory = speechHistory + recognizedText
            onRecognizedTextConsumed()
        }
    }

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
            label = { Text("Escribe", style = AccessibleTypography().bodyLarge) },
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
                        imageVector = Text_to_speech,
                        contentDescription = if (isSpeaking) "Detener lectura" else "Leer",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        if (isSpeaking) "Detener" else "Leer",
                        style = AccessibleTypography().labelLarge
                    )
                }
            }

            Button(
                onClick = { 
                    if (isListening) {
                    } else {
                        sttController?.startListening(activity) { }
                    }
                },
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
                        imageVector = Microphone,
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

        if (isListening) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AccesibleColors.Secondary.copy(alpha = 0.1f)
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
                        color = AccesibleColors.Secondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Escuchando...",
                        style = AccessibleTypography().bodyLarge,
                        color = AccesibleColors.Secondary
                    )
                }
            }
        }

        if (speechHistory.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AccesibleColors.Primary.copy(alpha = 0.05f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Historial de dictado",
                        style = AccessibleTypography().titleLarge,
                        color = AccesibleColors.Primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 200.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(speechHistory.reversed()) { speech ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = speech,
                                    style = AccessibleTypography().bodyMedium,
                                    color = AccesibleColors.OnBackground,
                                    modifier = Modifier.weight(1f)
                                )
                                IconButton(
                                    onClick = { 
                                        text = if (text.isBlank()) speech else "$text $speech"
                                    }
                                ) {
                                    Icon(
                                        imageVector = Text_to_speech,
                                        contentDescription = "Agregar al texto",
                                        tint = AccesibleColors.Primary
                                    )
                                }
                            }
                            Divider(color = AccesibleColors.Primary.copy(alpha = 0.1f))
                        }
                    }
                }
            }
        }
    }
}
