package com.tua.boorugalleryzero.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.tua.boorugalleryzero.presentation.screens.settings.SettingsMainScreen

@Composable
fun NavSettings(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(RouteSettings.Main)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<RouteSettings.Main> {
                SettingsMainScreen()
            }
        }
    )
}