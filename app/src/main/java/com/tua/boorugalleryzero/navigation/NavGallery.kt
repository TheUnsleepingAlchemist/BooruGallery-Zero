package com.tua.boorugalleryzero.navigation

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.tua.boorugalleryzero.data.model.FetchQuery
import com.tua.boorugalleryzero.data.persistent.GallerySettings
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
    gallerySettings: GallerySettings,
    gifLoader: () -> ImageLoader,
    onBackClick: () -> Unit
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

    val client = DanbooruJson(httpClient,gallerySettings.prodApi)

    val galleryViewModel = viewModel {
        GalleryViewModel(
            client = client,
            fetchQuery = FetchQuery("",client.initPage,gallerySettings.fetchQuantity)
//            fetchQuery = gallerySettings.fetchQuantity
        )
    }

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

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<RouteGallery.Main> {
                GalleryMainScreen(
                    viewModel = galleryViewModel,
                    settings = gallerySettings,
                    headers = headers,
                    onBackClick = { goBack() },
                    onClick = {
                        backStack.add(RouteGallery.Preview)
                    },
                    onSearch = {}
                )
            }
            entry<RouteGallery.Preview> {
                GalleryPreviewScreen(
                    viewModel = galleryViewModel,
                    settings = gallerySettings,
                    headers = headers,
                    gifLoader = gifLoader,
                    mediaSourceFactory = mediaSourceFactory,
                    onDetailsClick = { post ->
                        backStack.add(RouteGallery.Details(post))
                    }
                )
            }
            entry<RouteGallery.Details> { key ->
                GalleryDetailsScreen(
                    post = key.post,
                    onBackClick = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )

}