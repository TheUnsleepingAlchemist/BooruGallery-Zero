package com.tua.boorugalleryzero.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumFlexibleTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.tua.boorugalleryzero.presentation.components.SettingsListItem
import com.tua.boorugalleryzero.presentation.components.TextIconButton

@Composable
fun SettingsMainScreen(modifier: Modifier = Modifier) {

    val colors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Settings")
                },
                navigationIcon = {
                    TextIconButton("arrow_back",{})
                }
            )
        }
    ) { sPadding ->

        LazyColumn(
            modifier = Modifier.padding(sPadding),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap)
        ) {

            itemsIndexed(List(10) { index -> index }) { i, item ->

                val shapes = ListItemDefaults.segmentedShapes(i,10)

                SettingsListItem(
                    onClick = {},
                    shapes = shapes,
                    colors = colors,
                    icon = "display_settings",
                    title = "Item $item",
                    description = "Short description of item $item",
                )

            }

        }

    }

}