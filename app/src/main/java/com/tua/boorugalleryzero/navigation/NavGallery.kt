package com.tua.boorugalleryzero.navigation

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import coil3.ImageLoader
import coil3.network.NetworkHeaders
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

@OptIn(UnstableApi::class)
@Composable
fun NavGallery(
    modifier: Modifier = Modifier,
    gifLoader: () -> ImageLoader
) {
    val context = LocalContext.current.applicationContext

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

    val headers = {
        NetworkHeaders
            .Builder()
            .set("referer", client.referer)
            .build()
    }

    val mediaSourceFactory = {
        val httpFactory = DefaultHttpDataSource.Factory()
            .setDefaultRequestProperties(
                mapOf("Referer" to client.referer)
            )

        val dataSourceFactory = DefaultDataSource.Factory(context, httpFactory)
        ProgressiveMediaSource.Factory(dataSourceFactory)
    }

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<RouteGallery.Main> {
                GalleryMainScreen(
                    viewModel = galleryViewModel,
                    headers = headers,
                    onClick = {
                        backStack.add(RouteGallery.Preview)
                    },
                    onSearch = {}
                )
            }
            entry<RouteGallery.Preview> {
                GalleryPreviewScreen(
                    viewModel = galleryViewModel,
                    headers = headers,
                    gifLoader = gifLoader,
                    mediaSourceFactory = mediaSourceFactory
                )
            }
            entry<RouteGallery.Details> {
                GalleryDetailsScreen()
            }
        }
    )

}