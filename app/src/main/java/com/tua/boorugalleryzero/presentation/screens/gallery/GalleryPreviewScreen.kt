package com.tua.boorugalleryzero.presentation.screens.gallery

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.exoplayer.source.MediaSource
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.ImageLoader
import coil3.network.NetworkHeaders
import com.tua.boorugalleryzero.domain.FileType
import com.tua.boorugalleryzero.presentation.components.PreviewGif
import com.tua.boorugalleryzero.presentation.components.PreviewImage
import com.tua.boorugalleryzero.presentation.components.PreviewUnknown
import com.tua.boorugalleryzero.presentation.components.PreviewVideo
import com.tua.boorugalleryzero.presentation.components.ScaffoldWithToolbar
import com.tua.boorugalleryzero.presentation.components.TextIconButton
import com.tua.boorugalleryzero.viewmodel.GalleryViewModel

@Composable
fun GalleryPreviewScreen(
    viewModel: GalleryViewModel,
    headers: () -> NetworkHeaders,
    gifLoader: () -> ImageLoader,
    mediaSourceFactory: () -> MediaSource.Factory,
    modifier: Modifier = Modifier
) {

    val initialIndex by viewModel.initialIndex.collectAsStateWithLifecycle()
    val postPagingItems = viewModel.postPagingSource.collectAsLazyPagingItems()

    val pagerState = rememberPagerState(initialIndex) { postPagingItems.itemCount }

    val settledPage by remember {
        derivedStateOf { pagerState.settledPage }
    }

    var isToolbarVisible by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(settledPage) {
        viewModel.setInitialIndex(settledPage)
        isToolbarVisible = true
    }

    ScaffoldWithToolbar(
        modifier = modifier,
        isLoading = false,
        onRefresh = {},
        isToolbarVisible = isToolbarVisible,
        toolbarContent = {
            TextIconButton(
                "favorite",
                {}
            )
            TextIconButton(
                "link",
                {}
            )
            TextIconButton(
                "download",
                {}
            )
            TextIconButton(
                "info",
                {}
            )
        }
    ) {

        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = 1
        ) { page ->
            val post = postPagingItems[page]
            if (post != null) {
                when(post.fileType) {
                    FileType.Image -> {
                        PreviewImage(
                            url = post.fileUrl,
                            headers = headers
                        )
                    }
                    FileType.Gif -> {
                        PreviewGif(
                            url = post.fileUrl,
                            headers = headers,
                            gifLoader = gifLoader
                        )
                    }
                    FileType.Video -> {
                        if (settledPage == page) {
                            isToolbarVisible = false
                        }
                        PreviewVideo(
                            url = post.fileUrl,
                            mediaSourceFactory = mediaSourceFactory,
                            onClick = {},
                            canPlay = settledPage == page
                        )
                    }
                    FileType.Unsupported -> {
                        PreviewUnknown(
                            fileExt = post.fileExt
                        )
                    }
                }
            }

        }

    }

}