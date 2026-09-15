package com.aci.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aci.core.ui.theme.ACITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ACITopBar(
    title: String,
    onNavClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    /** Optional second line under the title — used for the signed-in member and sign-out. */
    subtitle: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                subtitle?.invoke()
            }
        },
        navigationIcon = {
            if (onNavClick != null) {
                IconButton(onClick = onNavClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Navigate back"
                    )
                }
            }
        },
        actions = actions,
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Light TopBar")
@Composable
private fun ACITopBarLightPreview() {
    ACITheme(useDarkTheme = false) {
        ACITopBar(
            title = "ACI Church App",
            onNavClick = {},
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        )
    }
}

@Preview(showBackground = true, name = "Tamil TopBar")
@Composable
private fun ACITopBarTamilPreview() {
    ACITheme(useDarkTheme = false) {
        ACITopBar(
            title = "அச்சி சர்ச்",
            onNavClick = {},
            actions = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Dark TopBar")
@Composable
private fun ACITopBarDarkPreview() {
    ACITheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ACITopBar(
                title = "ACI Church App",
                onNavClick = {},
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }
    }
}
