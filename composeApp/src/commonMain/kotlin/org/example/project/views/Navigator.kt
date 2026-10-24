package org.example.project.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

object Navigator {

    private val screens = mutableMapOf<String, Screen>()

    private val homeScreen: Screen = HomeScreen()
    private val detailScreen: Screen = DetailScreen()

    private val comentsScreen: Screen = comentsScreen()

    val defaultScreen: Screen = homeScreen

    private val _currentScreen = mutableStateOf<Screen>(defaultScreen)
    var currentScreen: Screen
        get() = _currentScreen.value
        set(value) {
            _currentScreen.value = value
        }

    init {
        screens[homeScreen.screenKey] = homeScreen
        screens[detailScreen.screenKey] = detailScreen
        screens[comentsScreen.screenKey] = comentsScreen
    }

    fun register(path: String, screen: Screen) {
        screens[path] = screen
    }

    fun navigateTo(path: String) {
        screens[path]?.let { screen ->
            currentScreen = screen
        }
    }

    @Composable
    fun Content() {
        currentScreen.execute()
    }
}