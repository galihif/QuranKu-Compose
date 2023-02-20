package com.giftech.quranku_compose.ui.navigation


sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Surah : Screen("surah/{nomorSurah}") {
        fun createRoute(nomorSurah: Int) = "surah/$nomorSurah"
    }
}