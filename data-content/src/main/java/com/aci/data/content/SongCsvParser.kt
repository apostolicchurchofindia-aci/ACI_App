package com.aci.data.content

import com.aci.core.domain.enum.Language
import com.aci.core.domain.enum.SongKey
import com.aci.core.domain.model.Song
import com.aci.core.domain.model.SongLyricSection
import kotlinx.datetime.Clock

/**
 * Parses the ACI song spreadsheet (see `SONG_IMPORT_TEMPLATE.csv` at the repo root for the
 * exact column layout) into domain models. Lyrics are one block of text per row, with
 * section headers in square brackets, e.g.:
 *
 * ```
 * [Pallavi]
 * line one
 * line two
 *
 * [Charanam 1]
 * line one
 * ```
 *
 * A row with no `[Section]` markers at all is treated as a single "Lyrics" section.
 */
object SongCsvParser {

    data class ParsedSongs(val songs: List<Song>, val lyrics: List<SongLyricSection>)

    private val sectionHeader = Regex("^\\[(.+)]$")

    fun parse(csvText: String): ParsedSongs {
        val rows = normalizeRows(parseCsvRows(csvText))
        if (rows.isEmpty()) return ParsedSongs(emptyList(), emptyList())

        val header = rows.first().map { it.trim().lowercase() }
        val col = header.withIndex().associate { (i, name) -> name to i }

        fun row(cells: List<String>, name: String): String =
            col[name]?.let { cells.getOrNull(it)?.trim() }.orEmpty()

        val songs = mutableListOf<Song>()
        val lyrics = mutableListOf<SongLyricSection>()

        rows.drop(1).filter { it.any(String::isNotBlank) }.forEachIndexed { rowIndex, cells ->
            val titleEn = row(cells, "title_en")
            if (titleEn.isBlank() && row(cells, "title_te").isBlank() && row(cells, "title_ta").isBlank()) {
                return@forEachIndexed
            }
            val id = row(cells, "id").ifBlank { "song-csv-${rowIndex + 1}" }
            val language = row(cells, "language")
            val key = row(cells, "key").uppercase().let { k -> runCatching { SongKey.valueOf(k) }.getOrNull() }
            val bpm = row(cells, "bpm").toIntOrNull()
            val songbookNumber = row(cells, "songbook_number").toIntOrNull()
            val lyricsLanguage = when (row(cells, "lyrics_language").lowercase()) {
                "ta", "tamil" -> Language.TAMIL
                "te", "telugu" -> Language.TELUGU
                else -> Language.ENGLISH
            }

            songs += Song(
                id = id,
                titleEn = titleEn.ifBlank { row(cells, "title_te").ifBlank { row(cells, "title_ta") } },
                titleTa = row(cells, "title_ta"),
                titleTe = row(cells, "title_te").ifBlank { null },
                titleTeTranslit = row(cells, "title_te_translit"),
                titleTaTranslit = row(cells, "title_ta_translit"),
                language = language,
                category = row(cells, "category"),
                key = key,
                bpm = bpm,
                composer = row(cells, "composer"),
                artist = row(cells, "artist"),
                hasLyrics = row(cells, "lyrics").isNotBlank(),
                songbookName = row(cells, "songbook_name"),
                songbookNumber = songbookNumber,
                createdAt = Clock.System.now()
            )

            lyrics += parseLyricSections(
                songId = id,
                lyricsBlock = row(cells, "lyrics"),
                language = lyricsLanguage,
                translitBlock = row(cells, "translit_lyrics"),
                translationTaBlock = row(cells, "translation_ta")
            )
        }

        return ParsedSongs(songs, lyrics)
    }

    private fun parseLyricSections(
        songId: String,
        lyricsBlock: String,
        language: Language,
        translitBlock: String,
        translationTaBlock: String
    ): List<SongLyricSection> {
        if (lyricsBlock.isBlank()) return emptyList()

        val translitSections = splitIntoSections(translitBlock)
        val translationTaSections = splitIntoSections(translationTaBlock)
        val sections = splitIntoSections(lyricsBlock)

        return sections.mapIndexed { index, (label, text) ->
            SongLyricSection(
                id = "$songId-lyr-$index",
                songId = songId,
                language = language,
                sectionLabel = label,
                lyrics = text,
                translitLyrics = translitSections.getOrNull(index)?.second.orEmpty(),
                translationTa = translationTaSections.getOrNull(index)?.second.orEmpty(),
                orderIndex = index
            )
        }
    }

    private fun splitIntoSections(block: String): List<Pair<String, String>> {
        if (block.isBlank()) return emptyList()
        val lines = block.replace("\r\n", "\n").split("\n")
        val sections = mutableListOf<Pair<String, MutableList<String>>>()

        for (line in lines) {
            val match = sectionHeader.matchEntire(line.trim())
            if (match != null) {
                sections += match.groupValues[1] to mutableListOf()
            } else if (line.isNotBlank()) {
                if (sections.isEmpty()) sections += "Lyrics" to mutableListOf()
                sections.last().second += line.trim()
            }
        }
        return sections.map { (label, textLines) -> label to textLines.joinToString("\n") }
    }

    /**
     * If the whole sheet was populated by pasting raw CSV text into a single spreadsheet column
     * (every row parses to exactly one cell, itself containing commas) rather than using real
     * columns, Google's CSV export just re-quotes that single cell per row. Detect that shape —
     * header row has 1 cell yet contains multiple comma-separated column names — and undo it by
     * rejoining every row's lone cell with "\n" and re-running the same RFC4180 parser over the
     * reconstructed text, which restores the original multi-line quoted fields (e.g. lyrics).
     */
    private fun normalizeRows(rows: List<List<String>>): List<List<String>> {
        if (rows.isEmpty()) return rows
        val header = rows.first()
        val looksCollapsed = header.size == 1 && header[0].count { it == ',' } >= 2
        if (!looksCollapsed) return rows
        val reconstructed = rows.joinToString("\n") { it.getOrElse(0) { "" } }
        return parseCsvRows(reconstructed)
    }

    /** Minimal RFC4180 CSV parser: handles quoted fields, embedded commas/newlines, and "" escaping. */
    private fun parseCsvRows(text: String): List<List<String>> {
        val rows = mutableListOf<List<String>>()
        var field = StringBuilder()
        var row = mutableListOf<String>()
        var inQuotes = false
        var i = 0
        while (i < text.length) {
            val c = text[i]
            when {
                inQuotes -> {
                    if (c == '"') {
                        if (i + 1 < text.length && text[i + 1] == '"') {
                            field.append('"'); i++
                        } else {
                            inQuotes = false
                        }
                    } else {
                        field.append(c)
                    }
                }
                c == '"' -> inQuotes = true
                c == ',' -> { row.add(field.toString()); field = StringBuilder() }
                c == '\n' -> { row.add(field.toString()); field = StringBuilder(); rows.add(row); row = mutableListOf() }
                c == '\r' -> {}
                else -> field.append(c)
            }
            i++
        }
        if (field.isNotEmpty() || row.isNotEmpty()) { row.add(field.toString()); rows.add(row) }
        return rows
    }
}
