package eam.xagh.unilocal.shared.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import eam.xagh.unilocal.R

val Ubuntu = FontFamily(
    Font(R.font.ubuntu_bold, FontWeight.Bold),
    Font(R.font.ubuntu_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.ubuntu_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.ubuntu_light, FontWeight.Light),
    Font(R.font.ubuntu_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.ubuntu_medium, FontWeight.Medium),
    Font(R.font.ubuntu_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.ubuntu_regular, FontWeight.Normal)
)

val Montserrat = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.montserrat_black, FontWeight.ExtraBold),
    Font(R.font.montserrat_blackitalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.montserrat_extralight, FontWeight.ExtraLight),
    Font(R.font.montserrat_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.montserrat_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
)

// Set of Material typography styles to start with
private val body = TextStyle(
    fontFamily = Ubuntu,
    letterSpacing = 0.5.sp,
    fontWeight = FontWeight.Normal,
)

private val title = TextStyle(
    fontFamily = Montserrat,
    letterSpacing = 0.5.sp,
)

val Typography = Typography(
    bodySmall = body.copy(
        fontSize = 12.sp,
        lineHeight = 14.4.sp,
    ),
    bodyMedium = body.copy(
        fontSize = 14.sp,
        lineHeight = 16.8.sp,
    ),
    bodyLarge = body.copy(
        fontSize = 16.sp,
        lineHeight = 19.2.sp,
        fontWeight = FontWeight.Bold
    ),
    titleSmall = title.copy(
        fontSize = 22.sp,
        lineHeight = 26.4.sp,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = title.copy(
        fontSize = 28.sp,
        lineHeight = 33.6.sp,
        fontWeight = FontWeight.Bold
    ),
    titleLarge = title.copy(
        fontSize = 34.sp,
        lineHeight = 40.8.sp,
        fontWeight = FontWeight.ExtraBold
    )
)