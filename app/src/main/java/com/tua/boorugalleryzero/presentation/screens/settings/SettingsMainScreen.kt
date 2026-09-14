package com.tua.boorugalleryzero.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.tua.boorugalleryzero.navigation.RouteSettings
import com.tua.boorugalleryzero.presentation.components.SettingsItem
import com.tua.boorugalleryzero.presentation.components.TextIconButton

@Composable
fun SettingsMainScreen(
    modifier: Modifier = Modifier,
    devtools: Boolean,
    onBackClick: () -> Unit,
    onItemClick: (NavKey) -> Unit
) {

    val allSections = listOf(
        SettingSection("Home","View type, Grid size","home", false,RouteSettings.Home),
        SettingSection("Gallery","Demo mode, Autoplay","gallery_thumbnail", false,RouteSettings.Gallery),
        SettingSection("Experimental","Demo mode, Autoplay","experiment", !devtools,RouteSettings.Dev),
        SettingSection("About","Version 0.0.1","info", false,RouteSettings.About),
    )

    val sections = allSections.filter { !it.hidden }

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
                    TextIconButton("arrow_back",onBackClick)
                }
            )
        }
    ) { sPadding ->

        LazyColumn(
            modifier = Modifier.padding(sPadding),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap)
        ) {

            itemsIndexed(
                items = sections,
                key = { _, item -> item.label }
            ) { i, item ->

                val shapes = ListItemDefaults.segmentedShapes(i,sections.size)

                SettingsItem(
                    onClick = {
                        onItemClick(item.destination)
                    },
                    shapes = shapes,
                    colors = colors,
                    icon = item.iconName,
                    title = item.label,
                    description = item.description,
                )

            }

        }

    }

}

data class SettingSection(
    val label: String,
    val description: String,
    val iconName: String,
    val hidden: Boolean,
    val destination: NavKey
)
