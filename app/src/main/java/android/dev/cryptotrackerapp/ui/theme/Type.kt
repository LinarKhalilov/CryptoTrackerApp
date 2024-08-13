package android.dev.cryptotrackerapp.ui.theme

import android.dev.cryptotrackerapp.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class Typography(
    val headers: Headers,
    val body: Body
)


class Body(
    val body1 : TextStyle,
    val body2 : TextStyle,
    val body3 : TextStyle,
    val body4 : TextStyle
) {
    companion object {
        fun getDefault() = Body(
            body1 = TextStyle(
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
                lineHeight = 18.75.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xFF525252)
            ),
            body2 = TextStyle(
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                color = Color.Black
            ),
            body3 = TextStyle(
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                lineHeight = 16.41.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xFF525252)
            ),
            body4 = TextStyle(
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
                lineHeight = 18.75.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xFF525252)
            )
        )
    }
}

class Headers(
    val h6 : TextStyle
) {
    companion object {
        fun getDefault() = Headers(
            h6 = TextStyle(
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                lineHeight = 23.44.sp,
                letterSpacing = 0.15.sp,
                color = Color.Black
            )
        )
    }
}