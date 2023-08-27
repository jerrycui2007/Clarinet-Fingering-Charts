package com.example.clarinetfingeringcharts

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object ClarinetScreen: Screen("clarinet_screen")
    object BassClarinetScreen: Screen("bass_clarinet_screen")
    object FingeringScreen: Screen("fingering_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }

}