package com.tua.boorugalleryzero.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumFlexibleTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.tua.boorugalleryzero.data.persistent.HomeSettings
import com.tua.boorugalleryzero.presentation.components.HomeItemGrid
import com.tua.boorugalleryzero.presentation.components.HomeItemList
import com.tua.boorugalleryzero.presentation.components.ScaffoldWithToolbar
import com.tua.boorugalleryzero.presentation.components.TextIconButton
import com.tua.boorugalleryzero.viewmodel.AppViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    settings: HomeSettings,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onAddClick: () -> Unit,
    onSettingsClick: () -> Unit,
    toggleViewType: () -> Unit
) {
    val listColors = ListItemDefaults.segmentedColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
    )

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    ScaffoldWithToolbar(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        isLoading = false,
        topBar = {
            TopAppBar(
                title = {
                    Text("Home")
                },
                scrollBehavior = scrollBehavior
            )
        },
        viewType = settings.viewType,
        toggleViewType = toggleViewType,
        gridSize = settings.gridSize,
        onRefresh = {},
        toolbarContent = {
            TextIconButton(
                "add",
                onAddClick
            )
            TextIconButton(
                "settings",
                onSettingsClick
            )
        },
        gridView = {
            items(100) {
                HomeItemGrid(onClick,it.toString(), modifier = Modifier.aspectRatio(1f))
            }
        },
        listView = {
            itemsIndexed(List(100) { index -> index }) { i, item ->
                val shapes = ListItemDefaults.segmentedShapes(i,100)
                HomeItemList(onClick, shapes = shapes, name = item.toString(), colors = listColors)
            }
        }
    )
}