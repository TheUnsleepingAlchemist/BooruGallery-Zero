package com.tua.boorugalleryzero.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.tua.boorugalleryzero.presentation.model.GridSize

@Composable
fun BasicGridView(
    modifier: Modifier = Modifier,
    state: LazyGridState,
    gridSize: GridSize = GridSize(2,5),
    content: LazyGridScope.() -> Unit
) {

    val orientation = LocalConfiguration.current.orientation

    val size = when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> gridSize.portrait
        else -> gridSize.landscape
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(size),
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        cacheWindow = LazyLayoutCacheWindow(aheadFraction = 1f, behindFraction = 0.5f),
        content = content
    )


}

