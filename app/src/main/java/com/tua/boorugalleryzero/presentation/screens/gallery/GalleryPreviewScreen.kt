package com.tua.boorugalleryzero.presentation.screens.gallery

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.tua.boorugalleryzero.presentation.components.ScaffoldWithToolbar
import com.tua.boorugalleryzero.presentation.components.TextIconButton

@Composable
fun GalleryPreviewScreen(modifier: Modifier = Modifier) {

    val pagerState = rememberPagerState { 100 }

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
        ) {
            AsyncImage(
                "https://placehold.co/450x800.png",
                null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

    }

}