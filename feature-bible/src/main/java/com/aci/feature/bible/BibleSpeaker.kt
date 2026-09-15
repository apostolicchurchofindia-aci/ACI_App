package com.aci.feature.bible

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.aci.core.domain.enum.Language
import java.util.Locale

/**
 * Speaks the chapter aloud using the device's text-to-speech engine.
 *
 * This is synthesised narration of the scripture text the app already ships — deliberately not
 * a bundled recording, since no narrated Tamil/Telugu audio has been licensed for this app.
 * Whether a given language actually speaks depends on the TTS voices installed on the device,
 * so [isLanguageAvailable] is false rather than silently doing nothing when a voice is missing.
 */
class BibleSpeaker(context: Context) {

    var isReady by mutableStateOf(false)
        private set
    var isSpeaking by mutableStateOf(false)
        private set
    var isLanguageAvailable by mutableStateOf(true)
        private set

    /** Index of the verse currently being spoken, or -1. Lets the reader highlight along. */
    var speakingIndex by mutableIntStateOf(-1)
        private set

    private var onFinished: (() -> Unit)? = null
    private var lastUtteranceId: String? = null

    private val tts = TextToSpeech(context.applicationContext) { status ->
        isReady = status == TextToSpeech.SUCCESS
    }

    init {
        tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                speakingIndex = utteranceId?.substringAfterLast('-')?.toIntOrNull() ?: -1
            }

            override fun onDone(utteranceId: String?) {
                if (utteranceId != null && utteranceId == lastUtteranceId) {
                    isSpeaking = false
                    speakingIndex = -1
                    onFinished?.invoke()
                }
            }

            @Deprecated("Required by the framework base class", ReplaceWith(""))
            override fun onError(utteranceId: String?) {
                isSpeaking = false
                speakingIndex = -1
            }
        })
    }

    private fun localeFor(language: Language): Locale = when (language) {
        Language.ENGLISH -> Locale.US
        Language.TAMIL -> Locale("ta", "IN")
        Language.TELUGU -> Locale("te", "IN")
    }

    /** Queues every verse; [onDone] fires only after the final one finishes. */
    fun speak(verses: List<String>, language: Language, onDone: () -> Unit) {
        if (!isReady || verses.isEmpty()) return

        val result = tts.setLanguage(localeFor(language))
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            isLanguageAvailable = false
            return
        }
        isLanguageAvailable = true

        onFinished = onDone
        lastUtteranceId = "verse-${verses.lastIndex}"
        isSpeaking = true

        verses.forEachIndexed { index, text ->
            val mode = if (index == 0) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD
            tts.speak(text, mode, null, "verse-$index")
        }
    }

    fun stop() {
        tts.stop()
        isSpeaking = false
        speakingIndex = -1
    }

    fun shutdown() {
        tts.stop()
        tts.shutdown()
    }
}

@Composable
fun rememberBibleSpeaker(): BibleSpeaker {
    val context = LocalContext.current
    val speaker = remember { BibleSpeaker(context) }
    DisposableEffect(Unit) {
        onDispose { speaker.shutdown() }
    }
    return speaker
}
