package com.tua.boorugalleryzero.presentation.screens.settings

import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tua.boorugalleryzero.data.persistent.defaultDevSettings
import com.tua.boorugalleryzero.presentation.components.SettingsList
import com.tua.boorugalleryzero.presentation.components.SettingsListInfoItem
import com.tua.boorugalleryzero.viewmodel.AppViewModel

@Composable
fun SettingsAboutScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    onBackClick: () -> Unit,
) {

    val settings by viewModel.devSettings.collectAsStateWithLifecycle(defaultDevSettings)

    var pressCount by remember { mutableIntStateOf(if(!settings.devtools) 0 else 10) }

    val topShape = ListItemDefaults.segmentedShapes(0,3)
    val middleShape = ListItemDefaults.segmentedShapes(1,3)
    val bottomShape = ListItemDefaults.segmentedShapes(2,3)

    val colors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    )

    SettingsList(
        modifier = modifier,
        label = "About",
        onBackClick = onBackClick
    ) {

        item {
            SettingsListInfoItem(
                onClick = {
                    if (pressCount != 7) {
                        pressCount += 1
                    }
                    if (!settings.devtools) {
                        viewModel.setDevtools(true)
                    }
                },
                shapes = topShape,
                colors = colors,
                label = "Version 0.0.1",
                description = "XYZ"
            )
        }
        item {
            SettingsListInfoItem(
                onClick = {},
                shapes = middleShape,
                colors = colors,
                label = "Made by",
                description = "Robert Lisowski"
            )
        }
        item {
            SettingsListInfoItem(
                onClick = {},
                shapes = bottomShape,
                colors = colors,
                label = "Extra button",
                description = "Just for fun"
            )
        }

    }

}