package com.tua.boorugalleryzero.presentation.screens.gallery

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.tua.boorugalleryzero.presentation.components.ScaffoldWithToolbar
import com.tua.boorugalleryzero.presentation.components.TextIconButton
import com.tua.boorugalleryzero.viewmodel.GalleryViewModel

@Composable
fun GalleryPreviewScreen(
    viewModel: GalleryViewModel,
    modifier: Modifier = Modifier
) {

    val initialIndex by viewModel.initialIndex.collectAsStateWithLifecycle()
    val postPagingItems = viewModel.postPagingSource.collectAsLazyPagingItems()

    val pagerState = rememberPagerState(initialIndex) { postPagingItems.itemCount }

    ScaffoldWithToolbar(
        modifier = modifier,
        isLoading = false,
        onRefresh = {},
        toolbarContent = {
            TextIconButton(
                "download",
                {}
            )
        }
    ) {

        HorizontalPager(
            state = pagerState
        ) { page ->
            AsyncImage(
                postPagingItems[page]!!.fileUrl,
//                "https://placehold.co/450x800.png",
                null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

    }

}