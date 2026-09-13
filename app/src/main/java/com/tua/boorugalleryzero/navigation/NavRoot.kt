package com.tua.boorugalleryzero.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.tua.boorugalleryzero.presentation.screens.home.HomeScreen

@Composable
fun NavRoot(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(RouteRoot.HomeScreen)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<RouteRoot.HomeScreen> {
                HomeScreen(
                    onClick = {
                        backStack.add(RouteRoot.GalleryScreen)
                    },
                    onAddClick = {},
                    onSettingsClick = {
                        backStack.add(RouteRoot.SettingsScreen)
                    }
                )
            }
            entry<RouteRoot.GalleryScreen> {
                NavGallery()
            }
            entry<RouteRoot.SettingsScreen> {
                NavSettings()
            }
        }
    )

}