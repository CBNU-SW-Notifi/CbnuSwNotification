package presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import cbnuswnotification.composeapp.generated.resources.Pretendard_Black
import cbnuswnotification.composeapp.generated.resources.Pretendard_Bold
import cbnuswnotification.composeapp.generated.resources.Pretendard_ExtraBold
import cbnuswnotification.composeapp.generated.resources.Pretendard_ExtraLight
import cbnuswnotification.composeapp.generated.resources.Pretendard_Light
import cbnuswnotification.composeapp.generated.resources.Pretendard_Medium
import cbnuswnotification.composeapp.generated.resources.Pretendard_Regular
import cbnuswnotification.composeapp.generated.resources.Pretendard_SemiBold
import cbnuswnotification.composeapp.generated.resources.Pretendard_Thin
import cbnuswnotification.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun pretendardFontFamily() = FontFamily(
    Font(Res.font.Pretendard_Light, weight = FontWeight.Light),
    Font(Res.font.Pretendard_Black, weight = FontWeight.Black),
    Font(Res.font.Pretendard_Regular, weight = FontWeight.Normal),
    Font(Res.font.Pretendard_Medium, weight = FontWeight.Medium),
    Font(Res.font.Pretendard_SemiBold, weight = FontWeight.SemiBold),
    Font(Res.font.Pretendard_Bold, weight = FontWeight.Bold),
    Font(Res.font.Pretendard_ExtraBold, weight = FontWeight.ExtraBold),
    Font(Res.font.Pretendard_ExtraLight, weight = FontWeight.ExtraLight),
    Font(Res.font.Pretendard_Thin, weight = FontWeight.Thin)
)

@Composable
fun pretendardTypography() = Typography().run {
    val fontFamily = pretendardFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily =  fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}