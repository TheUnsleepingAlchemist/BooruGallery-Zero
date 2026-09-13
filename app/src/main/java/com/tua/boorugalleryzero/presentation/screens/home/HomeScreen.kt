package com.tua.boorugalleryzero.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tua.boorugalleryzero.presentation.components.HomeItemGrid
import com.tua.boorugalleryzero.presentation.components.HomeItemList
import com.tua.boorugalleryzero.presentation.components.ScaffoldWithToolbar
import com.tua.boorugalleryzero.presentation.components.TextIconButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val listColors = ListItemDefaults.segmentedColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
    )

    ScaffoldWithToolbar(
        isLoading = false,
        onRefresh = {},
        toolbarContent = {
            TextIconButton(
                "add",
                {}
            )
            TextIconButton(
                "settings",
                {}
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