package com.tua.boorugalleryzero.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.tua.boorugalleryzero.presentation.screens.gallery.GalleryDetailsScreen
import com.tua.boorugalleryzero.presentation.screens.gallery.GalleryMainScreen
import com.tua.boorugalleryzero.presentation.screens.gallery.GalleryPreviewScreen

@Composable
fun NavGallery(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(RouteGallery.Main)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<RouteGallery.Main> {
                GalleryMainScreen()
            }
            entry<RouteGallery.Preview> {
                GalleryPreviewScreen()
            }
            entry<RouteGallery.Details> {
                GalleryDetailsScreen()
            }
        }
    )

}