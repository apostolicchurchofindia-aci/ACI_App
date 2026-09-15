package com.aci.church.app;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0007\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004J\u0016\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001bJ\u000e\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/aci/church/app/Routes;", "", "()V", "BIBLE", "", "BIBLE_BOOKMARKS", "BIBLE_CHAPTERS", "BIBLE_READER", "BIBLE_SEARCH", "EVENTS", "EVENT_DETAIL", "HOME", "LOGIN", "MY_ACI", "PLAN_DAY_READER", "PRAYER", "READING_PLAN", "REGISTER", "SONGS", "SONG_DETAIL", "SONG_REQUEST", "SONG_REQUESTS_ADMIN", "SUBMIT_PRAYER", "bibleChapters", "bookId", "bibleReader", "chapter", "", "eventDetail", "eventId", "planDayReader", "dayNumber", "songDetail", "songId", "app_debug"})
final class Routes {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HOME = "home";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BIBLE = "bible";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BIBLE_CHAPTERS = "bibleChapters/{bookId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BIBLE_READER = "bibleReader/{bookId}/{chapter}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BIBLE_SEARCH = "bibleSearch";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BIBLE_BOOKMARKS = "bibleBookmarks";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String READING_PLAN = "readingPlan";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PLAN_DAY_READER = "planDayReader/{dayNumber}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SONGS = "songs";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SONG_DETAIL = "songDetail/{songId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SONG_REQUEST = "songRequest";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SONG_REQUESTS_ADMIN = "songRequestsAdmin";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EVENTS = "events";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EVENT_DETAIL = "eventDetail/{eventId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PRAYER = "prayer";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBMIT_PRAYER = "submitPrayer";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MY_ACI = "myaci";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN = "login";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REGISTER = "register";
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.church.app.Routes INSTANCE = null;
    
    private Routes() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String songDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String songId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String bibleChapters(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String bibleReader(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, int chapter) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String eventDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String eventId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String planDayReader(int dayNumber) {
        return null;
    }
}