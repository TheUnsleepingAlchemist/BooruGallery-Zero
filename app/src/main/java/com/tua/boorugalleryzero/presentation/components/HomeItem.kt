package com.tua.boorugalleryzero.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun HomeItemGrid(
    onClick: () -> Unit,
    name: String,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
    ) {
        Box(
            modifier = modifier
        ) {
            AsyncImage(
                "https://placehold.co/200x300.png",
                null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            name,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
    }
}

@Composable
fun HomeItemList(
    onClick: () -> Unit,
    name: String,
    shapes: ListItemShapes,
    colors: ListItemColors,
    modifier: Modifier = Modifier,
) {

    SegmentedListItem(
        onClick = onClick,
        shapes = shapes,
        modifier = modifier,
        leadingContent = {
            AsyncImage(
                "https://placehold.co/200x300.png",
                null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )
        },
        colors = colors
    ) {
        Text(name)
    }

}