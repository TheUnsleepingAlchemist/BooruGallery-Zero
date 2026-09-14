package com.tua.boorugalleryzero.presentation.screens.gallery


import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import com.tua.boorugalleryzero.data.persistent.GallerySettings
import com.tua.boorugalleryzero.domain.Rating
import com.tua.boorugalleryzero.presentation.components.BasicGridView
import com.tua.boorugalleryzero.presentation.components.BottomSearch
import com.tua.boorugalleryzero.presentation.components.GalleryItem
import com.tua.boorugalleryzero.presentation.components.ScaffoldWithToolbar
import com.tua.boorugalleryzero.presentation.components.TextIconButton
import com.tua.boorugalleryzero.viewmodel.GalleryViewModel

@Composable
fun GalleryMainScreen(
    viewModel: GalleryViewModel,
    settings: GallerySettings,
    modifier: Modifier = Modifier,
    headers: () -> NetworkHeaders,
    onBackClick: () -> Unit,
    onClick: () -> Unit,
    onSearch: () -> Unit
) {

    val initialIndex by viewModel.initialIndex.collectAsStateWithLifecycle()

    val gridState = rememberLazyGridState(initialFirstVisibleItemIndex = initialIndex)

    val textFieldState = rememberTextFieldState()

    val postPagingItems = viewModel.postPagingSource.collectAsLazyPagingItems()


    LaunchedEffect(initialIndex) {
        gridState.scrollToItem(initialIndex)
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    ScaffoldWithToolbar(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        isLoading = postPagingItems.loadState.refresh is LoadState.Loading,
        topBar = {
            TopAppBar(
                title = {
                    Text("Gallery")
                },
                navigationIcon = {
                    TextIconButton(
                        iconName = "arrow_back",
                        onClick = onBackClick
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        onRefresh = {},
        toolbarContent = {
            BottomSearch(
                state = textFieldState,
                onSearch = onSearch
            )
        },
    ) {

        BasicGridView(
            state = gridState,
            gridSize = settings.gridSize
        ) {

            items(
                count = postPagingItems.itemCount,
                key = postPagingItems.itemKey { it.id }
            ) { index ->
                val post = postPagingItems[index]
                if (post != null) {

                    if (settings.demoMode && post.rating != Rating.General) {
                        GalleryItem(
                            onClick = {
                                viewModel.setInitialIndex(index)
                                onClick()
                            }
                        )
                    }
                    else {
                        val model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(post.previewUrl)
                            .httpHeaders(headers())
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
    
}