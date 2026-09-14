package com.tua.boorugalleryzero.presentation.screens.settings

import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tua.boorugalleryzero.data.persistent.PrefKeys
import com.tua.boorugalleryzero.data.persistent.defaultGallerySettings
import com.tua.boorugalleryzero.presentation.components.SettingsList
import com.tua.boorugalleryzero.presentation.components.SettingsListSliderItem
import com.tua.boorugalleryzero.presentation.components.SettingsListSwitchItem
import com.tua.boorugalleryzero.viewmodel.AppViewModel

@Composable
fun SettingsGalleryScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    onBackClick: () -> Unit,
) {

    val settings by viewModel.gallerySetting.collectAsStateWithLifecycle(defaultGallerySettings)

    val topShape = ListItemDefaults.segmentedShapes(0,3)
    val middleShape = ListItemDefaults.segmentedShapes(1,3)
    val bottomShape = ListItemDefaults.segmentedShapes(2,3)

    val colors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    )

    SettingsList(
        modifier = modifier,
        label = "Gallery",
        onBackClick = onBackClick
    ) {

        item {
            SettingsListSliderItem(
                onValueChange = { value -> viewModel.setGallerySizePortrait(value.toInt()) },
                value = settings.gridSize.portrait,
                valueRange = 2..8,
                shapes = topShape,
                colors = colors,
                label = PrefKeys.GALLERY_SIZE_PORTRAIT.label,
            )
        }
        item {
            SettingsListSliderItem(
                onValueChange = { value -> viewModel.setGallerySizeLandscape(value.toInt()) },
                value = settings.gridSize.landscape,
                valueRange = 2..8,
                shapes = middleShape,
                colors = colors,
                label = PrefKeys.GALLERY_SIZE_LANDSCAPE.label,
            )
        }
        item {
            SettingsListSliderItem(
                onValueChange = { value -> viewModel.setFetchQuantity(value.toInt()) },
                value = settings.fetchQuantity,
                valueRange = 20..100,
                shapes = middleShape,
                colors = colors,
                label = PrefKeys.FETCH_QUANTITY.label,
            )
        }
        item {
            SettingsListSwitchItem(
                onClick = {
                    viewModel.setAutoplayVideo(!settings.autoplayVideo)
                },
                checked = settings.autoplayVideo,
                shapes = middleShape,
                colors = colors,
                label = PrefKeys.AUTOPLAY_VIDEO.label,
            )
        }
        item {
            SettingsListSwitchItem(
                onClick = {
                    viewModel.setLoopVideo(!settings.loopVideo)
                },
                checked = settings.loopVideo,
                shapes = middleShape,
                colors = colors,
                label = PrefKeys.LOOP_VIDEO.label,
            )
        }
        item {
            SettingsListSwitchItem(
                onClick = {
                    viewModel.setMuteVideo(!settings.muteVideo)
                },
                checked = settings.muteVideo,
                shapes = bottomShape,
                colors = colors,
                label = PrefKeys.MUTE_VIDEO.label,
            )
        }

    }

}