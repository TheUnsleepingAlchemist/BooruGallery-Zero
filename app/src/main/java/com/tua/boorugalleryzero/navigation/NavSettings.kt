package com.tua.boorugalleryzero.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.tua.boorugalleryzero.data.persistent.defaultDevSettings
import com.tua.boorugalleryzero.data.persistent.defaultHomeSettings
import com.tua.boorugalleryzero.presentation.screens.settings.SettingsAboutScreen
import com.tua.boorugalleryzero.presentation.screens.settings.SettingsDevScreen
import com.tua.boorugalleryzero.presentation.screens.settings.SettingsGalleryScreen
import com.tua.boorugalleryzero.presentation.screens.settings.SettingsHomeScreen
import com.tua.boorugalleryzero.presentation.screens.settings.SettingsMainScreen
import com.tua.boorugalleryzero.viewmodel.AppViewModel
import kotlin.collections.removeLastOrNull
import kotlin.compareTo

@Composable
fun NavSettings(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    onBackClick: () -> Unit
) {

    val backStack = rememberNavBackStack(RouteSettings.Main)

    val canGoBack by remember {
        derivedStateOf {
            backStack.size > 1
        }
    }

    fun goBack() {
        if (canGoBack) {
            backStack.removeLastOrNull()
        }
        else {
            onBackClick()
        }
    }

    val settings by viewModel.devSettings.collectAsStateWithLifecycle(defaultDevSettings)


    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<RouteSettings.Main> {
                SettingsMainScreen(
                    devtools = settings.devtools,
                    onBackClick = { goBack() },
                    onItemClick = { route ->
                        backStack.add(route)
                    }
                )
            }
            entry<RouteSettings.Home> {
                SettingsHomeScreen(
                    viewModel = viewModel,
                    onBackClick = { goBack() },
//                    viewType = homeSettings.viewType,
//                    gridSize = homeSettings.gridSize
                )
            }
            entry<RouteSettings.Gallery> {
                SettingsGalleryScreen(
                    viewModel = viewModel,
                    onBackClick = { goBack() }
                )
            }
            entry<RouteSettings.Dev> {
                SettingsDevScreen(
                    viewModel = viewModel,
                    onBackClick = { goBack() }
                )
            }
            entry<RouteSettings.About> {
                SettingsAboutScreen(
                    viewModel = viewModel,
                    onBackClick = { goBack() }
                )
            }
        }
    )
}