package com.tua.boorugalleryzero.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@Composable
fun GalleryItem(
    modifier: Modifier = Modifier,
    imageModel: ImageRequest,
    onClick: () -> Unit
) {

    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        modifier = modifier.aspectRatio(1f)
    ) {
        Box {
            AsyncImage(
                imageModel,
                null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }

}