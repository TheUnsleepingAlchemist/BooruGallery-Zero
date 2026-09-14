package com.tua.boorugalleryzero.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.tua.boorugalleryzero.presentation.model.GridSize
import com.tua.boorugalleryzero.presentation.model.ViewType

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ScaffoldWithToolbar(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    isToolbarVisible: Boolean = true,
    toolbarContent: @Composable RowScope.() -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {}
) {

    val pullToRefreshState = rememberPullToRefreshState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            AnimatedVisibility(
                isToolbarVisible,
                enter = slideInVertically { 0 },
                exit = slideOutVertically { 150 }
            ) {
                ScaffoldToolbar(
                    content = toolbarContent
                )
            }
        }
    ) { sPadding ->

        PullToRefreshBox(
            isRefreshing = isLoading,
            onRefresh = onRefresh,
            modifier = Modifier.fillMaxSize().padding(sPadding),
            state = pullToRefreshState,
            indicator = {
                PullToRefreshDefaults.LoadingIndicator(
                    state = pullToRefreshState,
                    isRefreshing = isLoading,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        ) {

            content()

        }

    }

}


@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalFoundationApi::class)
@Composable
fun ScaffoldWithToolbar(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    topBar: @Composable () -> Unit = {},
    viewType: ViewType,
    toggleViewType: () -> Unit,
    gridSize: GridSize,
    onRefresh: () -> Unit,
    toolbarContent: @Composable RowScope.() -> Unit = {},
    gridView: LazyGridScope.() -> Unit,
    listView: LazyListScope.() -> Unit
) {

    val pullToRefreshState = rememberPullToRefreshState()

    val gridState = rememberLazyGridState()
    val listState = rememberLazyListState(
        LazyLayoutCacheWindow(aheadFraction = 1f, behindFraction = 0.5f)
    )

//    var viewType by rememberSaveable {
//        mutableStateOf(viewType)
//    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topBar,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ScaffoldToolbar {
                TextIconButton(
                    iconName = when(viewType) {
                        ViewType.Grid -> "grid_view"
                        ViewType.List -> "view_list"
                    },
//                    onClick = {
//                        viewType = when(viewType) {
//                            ViewType.Grid -> ViewType.List
//                            ViewType.List -> ViewType.Grid
//                        }
//                    }
                    onClick = toggleViewType
                )
                toolbarContent()
            }
        }
    ) { sPadding ->

        PullToRefreshBox(
            isRefreshing = isLoading,
            onRefresh = onRefresh,
            modifier = Modifier.fillMaxSize().padding(sPadding),
            state = pullToRefreshState,
            indicator = {
                PullToRefreshDefaults.LoadingIndicator(
                    state = pullToRefreshState,
                    isRefreshing = isLoading,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        ) {

            when(viewType) {
                ViewType.Grid -> {
                    BasicGridView(
                        state = gridState,
                        gridSize = gridSize,
                        content = gridView
                    )
                }
                ViewType.List -> {
                    BasicListView(
                        state = listState,
                        content = listView
                    )
                }
            }

        }

    }

}