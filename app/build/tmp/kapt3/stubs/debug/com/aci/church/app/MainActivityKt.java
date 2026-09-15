package com.aci.church.app;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0003*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u0007\u001a\u00020\u0003*\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"ACI_YOUTUBE_CHANNEL_URL", "", "ACIApp", "", "navigateSingleTopTo", "Landroidx/navigation/NavHostController;", "route", "openUrl", "Landroid/content/Context;", "url", "app_debug"})
public final class MainActivityKt {
    
    /**
     * ACI's official YouTube channel — the sermon archive the Home card links out to.
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ACI_YOUTUBE_CHANNEL_URL = "https://www.youtube.com/@theapostolicchurchofindia";
    
    /**
     * Hands the link to whatever app can open it; silently ignored if the device has none.
     */
    private static final void openUrl(android.content.Context $this$openUrl, java.lang.String url) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ACIApp() {
    }
    
    private static final void navigateSingleTopTo(androidx.navigation.NavHostController $this$navigateSingleTopTo, java.lang.String route) {
    }
}