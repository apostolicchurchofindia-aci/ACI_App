package com.aci.data.content;

/**
 * App-wide selected content language (English/Tamil/Telugu), chosen from the Home screen
 * dropdown. In-memory only — not persisted across process death, same as [AdminSession]/auth
 * state in this local-only build. Screens that have real translated content (Bible, songs)
 * read this to decide which language field to render, falling back to English wherever a
 * translation is missing for a given item.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\r"}, d2 = {"Lcom/aci/data/content/AppLanguageSession;", "", "()V", "_current", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/aci/core/domain/enum/Language;", "current", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrent", "()Lkotlinx/coroutines/flow/StateFlow;", "select", "", "language", "data-content_debug"})
public final class AppLanguageSession {
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<com.aci.core.domain.enum.Language> _current = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<com.aci.core.domain.enum.Language> current = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.data.content.AppLanguageSession INSTANCE = null;
    
    private AppLanguageSession() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.aci.core.domain.enum.Language> getCurrent() {
        return null;
    }
}