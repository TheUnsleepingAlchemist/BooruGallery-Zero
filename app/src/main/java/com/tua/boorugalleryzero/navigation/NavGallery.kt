package com.tua.boorugalleryzero.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import coil3.ImageLoader
import com.tua.boorugalleryzero.data.source.DanbooruJson
import com.tua.boorugalleryzero.presentation.screens.gallery.GalleryDetailsScreen
import com.tua.boorugalleryzero.presentation.screens.gallery.GalleryMainScreen
import com.tua.boorugalleryzero.presentation.screens.gallery.GalleryPreviewScreen
import com.tua.boorugalleryzero.viewmodel.GalleryViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Composable
fun NavGallery(
    modifier: Modifier = Modifier,
    gifLoader: ImageLoader
) {

    val backStack = rememberNavBackStack(RouteGallery.Main)

    val httpClient = HttpClient(OkHttp) {
        install(Logging) {
            logger = Logger.SIMPLE
        }
        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
    }

    val client = DanbooruJson(httpClient)

    val galleryViewModel = viewModel { GalleryViewModel(client) }

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<RouteGallery.Main> {
                GalleryMainScreen(
                    viewModel = galleryViewModel,
                    onClick = {
                        backStack.add(RouteGallery.Preview)
                    },
                    onSearch = {}
                )
            }
            entry<RouteGallery.Preview> {
                GalleryPreviewScreen(
                    viewModel = galleryViewModel,
                    gifLoader = gifLoader
                )
            }
            entry<RouteGallery.Details> {
                GalleryDetailsScreen()
            }
        }
    )

}