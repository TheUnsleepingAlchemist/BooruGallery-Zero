package com.tua.boorugalleryzero.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tua.boorugalleryzero.presentation.components.ScaffoldWithToolbar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val lazyGridState = rememberLazyGridState()

    ScaffoldWithToolbar(
        isLoading = false,
        onRefresh = {}
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = lazyGridState,
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            cacheWindow = LazyLayoutCacheWindow(aheadFraction = 1f, behindFraction = 0.5f)
        ) {

            items(100) {
                Card(
                    modifier = Modifier.aspectRatio(1f)
                ) {
                    Text("$it")
                }
            }

        }
    }

}