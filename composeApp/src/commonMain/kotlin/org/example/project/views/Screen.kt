package org.example.project.views

import androidx.compose.runtime.Composable

abstract class Screen {
    abstract val screenKey: String

    @Composable
    abstract fun execute(): Unit
}