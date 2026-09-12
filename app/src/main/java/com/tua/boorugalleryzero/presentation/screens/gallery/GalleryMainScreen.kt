package com.tua.boorugalleryzero.presentation.screens.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.tua.boorugalleryzero.presentation.components.BasicGridView
import com.tua.boorugalleryzero.presentation.components.BottomSearch
import com.tua.boorugalleryzero.presentation.components.ScaffoldWithToolbar
import com.tua.boorugalleryzero.presentation.components.TextIconButton

@Composable
fun GalleryMainScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onSearch: () -> Unit
) {

    val gridState = rememberLazyGridState()

    val textFieldState = rememberTextFieldState()

    ScaffoldWithToolbar(
        modifier = modifier,
        isLoading = false,
        onRefresh = {},
        toolbarContent = {
            BottomSearch(
                state = textFieldState,
                onSearch = onSearch
            )
        },
    ) {

        BasicGridView(
            state = gridState
        ) {

            items(100) {

                Surface(
                    onClick = onClick,
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