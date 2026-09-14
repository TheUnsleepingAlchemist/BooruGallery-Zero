package com.tua.boorugalleryzero.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedListItem
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsListSwitchItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    checked: Boolean,
    shapes: ListItemShapes,
    colors: ListItemColors,
    label: String,
    description: String
) {

    SegmentedListItem(
        onClick = onClick,
        shapes = shapes,
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = null
            )
        },
        supportingContent = {
            Text(description)
        },
        colors = colors
    ) {
        Text(label)
    }
}

@Composable
fun SettingsListInfoItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shapes: ListItemShapes,
    colors: ListItemColors,
    label: String,
    description: String
) {

    SegmentedListItem(
        onClick = onClick,
        shapes = shapes,
        modifier = modifier,
        supportingContent = {
            Text(description)
        },
        colors = colors
    ) {
        Text(label)
    }
}

@Composable
fun SettingsListSwitchItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    checked: Boolean,
    shapes: ListItemShapes,
    colors: ListItemColors,
    label: String
) {

    SegmentedListItem(
        onClick = onClick,
        shapes = shapes,
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = null
            )
        },
        colors = colors
    ) {
        Text(label)
    }
}

@Composable
fun SettingsListSliderItem(
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
    value: Int,
    valueRange: ClosedRange<Int>,
    shapes: ListItemShapes,
    colors: ListItemColors,
    label: String,
) {

    SegmentedListItem(
        shapes = shapes,
        modifier = modifier,
        trailingContent = {
            Text(
                value.toString(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        supportingContent = {
            Slider(
                value = value.toFloat(),
                valueRange = valueRange.start.toFloat()..valueRange.endInclusive.toFloat(),
                modifier = Modifier.fillMaxWidth(0.75f),
                onValueChange = onValueChange
            )
        },
        colors = colors
    ) {
        Text(label)
    }
}