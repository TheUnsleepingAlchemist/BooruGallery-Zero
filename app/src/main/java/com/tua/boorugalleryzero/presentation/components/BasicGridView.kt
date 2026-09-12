package com.tua.boorugalleryzero.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BasicGridView(
    modifier: Modifier = Modifier,
    state: LazyGridState,
    content: LazyGridScope.() -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(175.dp),
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        cacheWindow = LazyLayoutCacheWindow(aheadFraction = 1f, behindFraction = 0.5f),
        content = content
    )


}

