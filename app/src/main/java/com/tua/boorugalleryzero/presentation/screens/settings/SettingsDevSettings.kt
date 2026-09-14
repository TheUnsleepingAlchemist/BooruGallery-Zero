package com.tua.boorugalleryzero.presentation.screens.settings

import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tua.boorugalleryzero.data.persistent.PrefKeys
import com.tua.boorugalleryzero.data.persistent.defaultDevSettings
import com.tua.boorugalleryzero.presentation.components.SettingsList
import com.tua.boorugalleryzero.presentation.components.SettingsListSwitchItem
import com.tua.boorugalleryzero.viewmodel.AppViewModel

@Composable
fun SettingsDevScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    onBackClick: () -> Unit,
) {

    val settings by viewModel.devSettings.collectAsStateWithLifecycle(defaultDevSettings)

    val topShape = ListItemDefaults.segmentedShapes(0,3)
    val bottomShape = ListItemDefaults.segmentedShapes(1,2)

    val colors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    )

    SettingsList(
        modifier = modifier,
        label = "Dev",
        onBackClick = onBackClick
    ) {

        item {
            SettingsListSwitchItem(
                onClick = {
                    viewModel.setDevtools(!settings.devtools)
                },
                checked = settings.devtools,
                shapes = topShape,
                colors = colors,
                label = PrefKeys.ENABLE_DEVTOOLS.label,
            )
        }
        item {
            SettingsListSwitchItem(
                onClick = {
                    viewModel.setDemoMode(!settings.demoMode)
                },
                checked = settings.demoMode,
                shapes = bottomShape,
                colors = colors,
                label = PrefKeys.DEMO_MODE.label,
            )
        }

    }

}