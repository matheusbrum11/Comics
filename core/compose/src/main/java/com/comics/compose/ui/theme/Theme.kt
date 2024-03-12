package com.comics.compose.ui.theme

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
)

@Composable
fun ComicsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Preview
@Composable
fun LightColorsPreview() {
    ComicsTheme(darkTheme = false) {
        ColorList()
    }
}

@Preview
@Composable
fun DarkColorsPreview() {
    ComicsTheme(darkTheme = true) {
        ColorList()
    }
}

@Composable
fun ColorList() {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .fillMaxHeight()
    ) {
        mapOf(
            "primary" to MaterialTheme.colorScheme.primary,
            "primaryVariant" to MaterialTheme.colorScheme.primaryContainer,
            "primarySurface" to MaterialTheme.colorScheme.onPrimaryContainer,
            "onPrimary" to MaterialTheme.colorScheme.onPrimary,
            "inversePrimary" to MaterialTheme.colorScheme.inversePrimary,

            "secondary" to MaterialTheme.colorScheme.secondary,
            "onSecondary" to MaterialTheme.colorScheme.onSecondary,
            "secondaryVariant" to MaterialTheme.colorScheme.secondaryContainer,
            "onSecondaryContainer" to MaterialTheme.colorScheme.onSecondaryContainer,

            "tertiary" to MaterialTheme.colorScheme.tertiary,
            "onTertiary" to MaterialTheme.colorScheme.onTertiary,
            "tertiaryContainer" to MaterialTheme.colorScheme.tertiaryContainer,
            "onTertiaryContainer" to MaterialTheme.colorScheme.onTertiaryContainer,

            "background" to MaterialTheme.colorScheme.background,
            "onBackground" to MaterialTheme.colorScheme.onBackground,

            "surface" to MaterialTheme.colorScheme.surface,
            "onSurface" to MaterialTheme.colorScheme.onSurface,
            "surfaceVariant" to MaterialTheme.colorScheme.surfaceVariant,
            "onSurfaceVariant" to MaterialTheme.colorScheme.onSurfaceVariant,
            "surfaceTint" to MaterialTheme.colorScheme.surfaceTint,
            "inverseSurface" to MaterialTheme.colorScheme.inverseSurface,
            "inverseOnSurface" to MaterialTheme.colorScheme.inverseOnSurface,

            "error" to MaterialTheme.colorScheme.error,
            "onError" to MaterialTheme.colorScheme.onError,
            "errorContainer" to MaterialTheme.colorScheme.errorContainer,
            "onErrorContainer" to MaterialTheme.colorScheme.onErrorContainer,
            "outline" to MaterialTheme.colorScheme.outline,

            // custom extented colors
        ).forEach { (text, color) ->
            Row {
                Text(
                    text = text,
                    color = Color.White,
                    modifier = Modifier
                        .weight(1f)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(32.dp)
                        .background(color)
                )
            }
        }
    }
}