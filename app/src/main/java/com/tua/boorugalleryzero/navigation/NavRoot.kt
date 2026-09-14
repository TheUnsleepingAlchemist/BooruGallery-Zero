package com.tua.boorugalleryzero.navigation

import android.os.Build.VERSION.SDK_INT
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import coil3.ImageLoader
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import com.tua.boorugalleryzero.data.persistent.HomeSettings
import com.tua.boorugalleryzero.data.persistent.defaultGallerySettings
import com.tua.boorugalleryzero.presentation.model.ViewType
import com.tua.boorugalleryzero.presentation.screens.home.HomeScreen
import com.tua.boorugalleryzero.viewmodel.AppViewModel

@Composable
fun NavRoot(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    homeSettings: HomeSettings
) {

    val backStack = rememberNavBackStack(RouteRoot.HomeScreen)

    val gallerySettings by viewModel.gallerySetting.collectAsStateWithLifecycle(
        defaultGallerySettings
    )

    val context = LocalContext.current.applicationContext

    val gifLoader = {
        ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(AnimatedImageDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<RouteRoot.HomeScreen> {
                HomeScreen(
                    settings = homeSettings,
                    onClick = {
                        backStack.add(RouteRoot.GalleryScreen)
                    },
                    onAddClick = {},
                    onSettingsClick = {
                        backStack.add(RouteRoot.SettingsScreen)
                    },
                    toggleViewType = {
                        viewModel.setViewType(when(homeSettings.viewType) {
                            ViewType.Grid -> false
                            ViewType.List -> true
                        })
                    }
                )
            }
            entry<RouteRoot.GalleryScreen> {
                NavGallery(
                    gallerySettings = gallerySettings,
                    gifLoader = gifLoader,
                    onBackClick = {
                        backStack.removeLastOrNull()
                    }
                )
            }
            entry<RouteRoot.SettingsScreen> {
                NavSettings(
                    viewModel = viewModel,
                    onBackClick = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )

}