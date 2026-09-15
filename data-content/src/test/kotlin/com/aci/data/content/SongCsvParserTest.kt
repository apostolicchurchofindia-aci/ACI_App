package com.aci.data.content

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class SongCsvParserTest {

    @Test
    fun `parses the repo-root song import template`() {
        val csvFile = File("../SONG_IMPORT_TEMPLATE.csv")
        assertTrue("SONG_IMPORT_TEMPLATE.csv not found at repo root", csvFile.exists())

        val parsed = SongCsvParser.parse(csvFile.readText())

        assertEquals(2, parsed.songs.size)

        val aayane = parsed.songs.first { it.id == "song-aayane-naa-sangeethamu" }
        assertEquals("ఆయనే నా సంగీతము", aayane.titleTe)
        assertEquals("Aayane Naa Sangeethamu", aayane.titleTeTranslit)
        assertEquals("Joyful Journey", aayane.songbookName)
        assertEquals(121, aayane.songbookNumber)

        val aayaneLyrics = parsed.lyrics.filter { it.songId == aayane.id }.sortedBy { it.orderIndex }
        assertEquals(listOf("Chorus", "Verse 1", "Verse 2", "Verse 3"), aayaneLyrics.map { it.sectionLabel })
        assertTrue(aayaneLyrics[0].lyrics.contains("ఆయనే నా సంగీతము బలమైన కోటయును"))
        assertTrue(aayaneLyrics[0].translitLyrics.contains("Aayane naa sangeethamu"))
        assertEquals("", aayaneLyrics[1].translitLyrics)

        val amazingGrace = parsed.songs.first { it.id == "song-amazing-grace" }
        assertEquals("G", amazingGrace.key?.name)
        assertEquals(72, amazingGrace.bpm)
        val agLyrics = parsed.lyrics.filter { it.songId == amazingGrace.id }.sortedBy { it.orderIndex }
        assertEquals(listOf("Verse 1", "Verse 2"), agLyrics.map { it.sectionLabel })
    }

    @Test
    fun `parses the live ACI spreadsheet CSV export, whose rows were pasted as raw text into a single column`() {
        // Captured verbatim from RemoteSongSource's export URL: the sheet's rows aren't real
        // spreadsheet columns (someone pasted whole CSV lines into column A), so Google's export
        // re-quotes each row as one cell. normalizeRows() must detect and undo that collapse.
        val csvFile = File("src/test/resources/live_sheet_sample.csv")
        assertTrue("live_sheet_sample.csv not found", csvFile.exists())

        val parsed = SongCsvParser.parse(csvFile.readText())

        assertEquals(2, parsed.songs.size)
        val aayane = parsed.songs.first { it.id == "song-aayane-naa-sangeethamu" }
        assertEquals("ఆయనే నా సంగీతము", aayane.titleTe)
        assertEquals(121, aayane.songbookNumber)
        val aayaneLyrics = parsed.lyrics.filter { it.songId == aayane.id }.sortedBy { it.orderIndex }
        assertEquals(listOf("Chorus", "Verse 1", "Verse 2", "Verse 3"), aayaneLyrics.map { it.sectionLabel })

        val amazingGrace = parsed.songs.first { it.id == "song-amazing-grace" }
        assertEquals("G", amazingGrace.key?.name)
        assertEquals(72, amazingGrace.bpm)
    }

    @Test
    fun `round-trips the full bilingual catalog CSV generated for Drive import`() {
        val csvFile = File("src/test/resources/full_catalog_check.csv")
        assertTrue("full_catalog_check.csv not found", csvFile.exists())

        val parsed = SongCsvParser.parse(csvFile.readText())

        assertEquals(53, parsed.songs.size)
        assertTrue(parsed.songs.all { it.titleTeTranslit.isNotBlank() || it.titleTe.isNullOrBlank() })

        val tamilSong = parsed.songs.first { it.id == "song-ennai-kaanbavarae" }
        assertEquals("Tamil", tamilSong.language)
        assertEquals("என்னைக் காண்பவரே", tamilSong.titleTa)
        assertTrue("metadata-only entry should have no lyrics yet", tamilSong.hasLyrics.not())
    }
}
