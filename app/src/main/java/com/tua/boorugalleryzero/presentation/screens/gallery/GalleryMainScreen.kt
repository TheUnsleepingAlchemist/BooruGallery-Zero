package com.tua.boorugalleryzero.presentation.screens.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import com.tua.boorugalleryzero.presentation.components.BasicGridView
import com.tua.boorugalleryzero.presentation.components.BottomSearch
import com.tua.boorugalleryzero.presentation.components.GalleryItem
import com.tua.boorugalleryzero.presentation.components.ScaffoldWithToolbar
import com.tua.boorugalleryzero.viewmodel.GalleryViewModel

@Composable
fun GalleryMainScreen(
    viewModel: GalleryViewModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onSearch: () -> Unit
) {

    val gridState = rememberLazyGridState()

    val textFieldState = rememberTextFieldState()

    val postPagingItems = viewModel.postPagingSource.collectAsLazyPagingItems()

    val headers = NetworkHeaders
        .Builder()
        .set("referer", viewModel.client.referer)
        .build()

    ScaffoldWithToolbar(
        modifier = modifier,
        isLoading = postPagingItems.loadState.refresh is LoadState.Loading,
        onRefresh = {},
        toolbarContent = {
            BottomSearch(
                state = textFieldState,
                onSearch = onSearch
            )
        },
    ) {

        BasicGridView(
            state = gridState
        ) {

            items(
                count = postPagingItems.itemCount,
                key = postPagingItems.itemKey { it.id }
            ) { index ->
                val post = postPagingItems[index]
                if (post != null) {

                    val model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(post.previewUrl)
                        .httpHeaders(headers)
                        .build()

                    GalleryItem(
                        imageModel = model,
                        onClick = {
                            viewModel.setInitialIndex(index)
                            onClick()
                        }
                    )

                }

            }
        }

    }
    
}