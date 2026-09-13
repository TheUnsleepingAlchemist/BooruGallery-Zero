package com.tua.boorugalleryzero.navigation

import android.os.Build.VERSION.SDK_INT
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import coil3.ImageLoader
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import com.tua.boorugalleryzero.presentation.screens.home.HomeScreen

@Composable
fun NavRoot(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(RouteRoot.HomeScreen)

    val gifLoader = ImageLoader.Builder(LocalContext.current.applicationContext)
        .components {
            if (SDK_INT >= 28) {
                add(AnimatedImageDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

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