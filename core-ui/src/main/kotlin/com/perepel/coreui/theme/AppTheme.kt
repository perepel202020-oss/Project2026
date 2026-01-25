package com.perepel.coreui.theme

/**
 * Тема приложения "2026"
 * Минималистичная тема с чёрно-белой палитрой и красными акцентами
 */
object AppTheme {
    data class Colors(
        val primary: Color = Color.Black,
        val background: Color = Color.White,
        val surface: Color = Color.White,
        val onPrimary: Color = Color.White,
        val onBackground: Color = Color.Black,
        val onSurface: Color = Color.Black,
        val error: Color = Color.Red,
        val success: Color = Color.Green,
        val warning: Color = Color.Yellow,
        val disabled: Color = Color.Gray
    )

    data class Typography(
        val fontFamily: String = "Roboto Mono",
        val h1: TextStyle = TextStyle(size = 24.0, weight = FontWeight.Bold),
        val h2: TextStyle = TextStyle(size = 20.0, weight = FontWeight.SemiBold),
        val body1: TextStyle = TextStyle(size = 16.0, weight = FontWeight.Normal),
        val body2: TextStyle = TextStyle(size = 14.0, weight = FontWeight.Normal),
        val caption: TextStyle = TextStyle(size = 12.0, weight = FontWeight.Light)
    )

    data class Color(val hex: String) {
        companion object {
            val Black = Color("#000000")
            val White = Color("#FFFFFF")
            val Red = Color("#FF5252")
            val Green = Color("#4CAF50")
            val Yellow = Color("#FFC107")
            val Gray = Color("#9E9E9E")
        }
    }

    data class TextStyle(
        val size: Double,
        val weight: FontWeight
    )

    enum class FontWeight {
        Light, Normal, SemiBold, Bold
    }

    val colors = Colors()
    val typography = Typography()

    fun getThemeInfo(): String {
        return """
        |Тема приложения "2026":
        |  Цвета: 
        |    - Основной: ${colors.primary.hex}
        |    - Фон: ${colors.background.hex}
        |    - Акцент: ${colors.error.hex}
        |  Шрифт: ${typography.fontFamily}
        |  Размеры текста: 
        |    - Заголовок 1: ${typography.h1.size}sp
        |    - Основной текст: ${typography.body1.size}sp
        """.trimMargin()
    }
}