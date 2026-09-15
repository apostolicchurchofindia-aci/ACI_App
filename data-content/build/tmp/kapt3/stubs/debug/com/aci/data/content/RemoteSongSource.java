package com.aci.data.content;

/**
 * Fetches the live ACI song spreadsheet and parses it with [SongCsvParser]. The sheet must be
 * shared as "Anyone with the link" (Viewer) for the CSV export endpoint to return data — see
 * `SONG_IMPORT_TEMPLATE.csv` at the repo root for the expected column layout.
 *
 * Returns null on any failure (no sharing, offline, empty sheet, parse error) so callers can
 * fall back to the bundled JSON seed without crashing.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\tH\u0086@\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/aci/data/content/RemoteSongSource;", "", "()V", "EXPORT_CSV_URL", "", "SHEET_ID", "TIMEOUT_MS", "", "tryFetch", "Lcom/aci/data/content/SongCsvParser$ParsedSongs;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data-content_debug"})
public final class RemoteSongSource {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SHEET_ID = "1H1bxcekBmwI4tyj2TxgD-ieo7RqfhfCul_cvo9lzs_k";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXPORT_CSV_URL = "https://docs.google.com/spreadsheets/d/1H1bxcekBmwI4tyj2TxgD-ieo7RqfhfCul_cvo9lzs_k/export?format=csv";
    private static final int TIMEOUT_MS = 8000;
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.data.content.RemoteSongSource INSTANCE = null;
    
    private RemoteSongSource() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object tryFetch(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.aci.data.content.SongCsvParser.ParsedSongs> $completion) {
        return null;
    }
}