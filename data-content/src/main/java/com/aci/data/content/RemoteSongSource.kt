package com.aci.data.content

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.net.HttpURLConnection
import java.net.URL

/**
 * Fetches the live ACI song spreadsheet and parses it with [SongCsvParser]. The sheet must be
 * shared as "Anyone with the link" (Viewer) for the CSV export endpoint to return data — see
 * `SONG_IMPORT_TEMPLATE.csv` at the repo root for the expected column layout.
 *
 * Returns null on any failure (no sharing, offline, empty sheet, parse error) so callers can
 * fall back to the bundled JSON seed without crashing.
 */
object RemoteSongSource {

    private const val SHEET_ID = "1H1bxcekBmwI4tyj2TxgD-ieo7RqfhfCul_cvo9lzs_k"
    private const val EXPORT_CSV_URL =
        "https://docs.google.com/spreadsheets/d/$SHEET_ID/export?format=csv"
    private const val TIMEOUT_MS = 8_000

    suspend fun tryFetch(): SongCsvParser.ParsedSongs? = withContext(Dispatchers.IO) {
        runCatching {
            val connection = (URL(EXPORT_CSV_URL).openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                connectTimeout = TIMEOUT_MS
                readTimeout = TIMEOUT_MS
                setRequestProperty("User-Agent", "Mozilla/5.0 (ACIChurchApp)")
            }
            connection.inputStream.bufferedReader().use { it.readText() }
        }.onFailure {
            Timber.w(it, "RemoteSongSource: fetch failed, will fall back to bundled JSON")
        }.getOrNull()?.let { csvText ->
            if (csvText.isBlank()) {
                Timber.w("RemoteSongSource: sheet returned empty (check sharing is 'Anyone with the link')")
                null
            } else {
                val parsed = SongCsvParser.parse(csvText)
                if (parsed.songs.isEmpty()) null else parsed
            }
        }
    }
}
