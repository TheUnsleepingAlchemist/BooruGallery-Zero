package com.tua.boorugalleryzero.presentation.screens.settings

import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tua.boorugalleryzero.data.persistent.PrefKeys
import com.tua.boorugalleryzero.data.persistent.defaultHomeSettings
import com.tua.boorugalleryzero.presentation.components.SettingsList
import com.tua.boorugalleryzero.presentation.components.SettingsListSliderItem
import com.tua.boorugalleryzero.presentation.components.SettingsListSwitchItem
import com.tua.boorugalleryzero.presentation.model.ViewType
import com.tua.boorugalleryzero.viewmodel.AppViewModel

@Composable
fun SettingsHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    onBackClick: () -> Unit,
) {

    val settings by viewModel.homeSetting.collectAsStateWithLifecycle(defaultHomeSettings)

    val topShape = ListItemDefaults.segmentedShapes(0,3)
    val middleShape = ListItemDefaults.segmentedShapes(1,3)
    val bottomShape = ListItemDefaults.segmentedShapes(2,3)

    val colors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    )

    SettingsList(
        modifier = modifier,
        label = "Home",
        onBackClick = onBackClick
    ) {

        item {
            SettingsListSwitchItem(
                onClick = {
                    viewModel.setViewType(!ViewType.toBoolean(settings.viewType))
                },
                checked = ViewType.toBoolean(settings.viewType),
                shapes = topShape,
                colors = colors,
                label = PrefKeys.VIEW_TYPE.label,
                description = settings.viewType.name
            )
        }
        item {
            SettingsListSliderItem(
                onValueChange = { value -> viewModel.setHomeSizePortrait(value.toInt()) },
                value = settings.gridSize.portrait,
                valueRange = 2..8,
                shapes = middleShape,
                colors = colors,
                label = PrefKeys.HOME_SIZE_PORTRAIT.label,
            )
        }
        item {
            SettingsListSliderItem(
                onValueChange = { value ->viewModel.setHomeSizeLandscape(value.toInt()) },
                value = settings.gridSize.landscape,
                valueRange = 2..8,
                shapes = bottomShape,
                colors = colors,
                label = PrefKeys.HOME_SIZE_LANDSCAPE.label,
            )
        }

    }

}