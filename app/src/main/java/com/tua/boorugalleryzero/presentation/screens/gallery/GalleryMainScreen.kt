package com.tua.boorugalleryzero.presentation.screens.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.tua.boorugalleryzero.presentation.components.BasicGridView
import com.tua.boorugalleryzero.presentation.components.ScaffoldWithToolbar

@Composable
fun GalleryMainScreen(modifier: Modifier = Modifier) {

    val gridState = rememberLazyGridState()

    ScaffoldWithToolbar(
        isLoading = false,
        onRefresh = {},
        toolbarContent = {},
    ) {

        BasicGridView(
            state = gridState
        ) {

            items(100) {

                Surface(
                    onClick = {},
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.aspectRatio(1f)
                ) {
                    Box() {
                        AsyncImage(
                            "https://placehold.co/200x200.png",
                            null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

            }

        }

    }
    
}