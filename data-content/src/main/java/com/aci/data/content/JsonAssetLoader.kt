package com.aci.data.content

import android.content.Context
import kotlinx.serialization.json.Json

/** Reads a JSON file from `assets/data/` and decodes it. All seed content lives as JSON, not Kotlin literals. */
object JsonAssetLoader {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    inline fun <reified T> load(context: Context, assetPath: String): T {
        val text = context.assets.open(assetPath).bufferedReader().use { it.readText() }
        return json.decodeFromString(text)
    }
}
