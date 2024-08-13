package android.dev.cryptotrackerapp.ui.theme.palette

import androidx.compose.ui.graphics.Color

data class Colors(
    val primaryColor : Color,
    val secondaryColor : Color,
    val backgroundColor : Color,
    val typographyColors: TypographyColors
)

data class TypographyColors(
    val primaryTextColor : Color,
    val secondaryTextColor : Color,
    val hintTextColor: Color
)

val lightPalette = Colors(
    primaryColor = LightOrange,
    secondaryColor = Color.Black,
    backgroundColor = Color.White,
    typographyColors = TypographyColors(
        primaryTextColor = Color.Black,
        secondaryTextColor = Color(0xFF525252),
        hintTextColor = Color(0xFFB9B9B9)
    )
)