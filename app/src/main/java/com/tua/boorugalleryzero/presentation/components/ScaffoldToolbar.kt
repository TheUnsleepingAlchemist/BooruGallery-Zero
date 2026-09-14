package com.tua.boorugalleryzero.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScaffoldToolbar(
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()

    HorizontalFloatingToolbar(
        modifier = modifier.imePadding().heightIn(max = FloatingToolbarDefaults.ContainerSize),
        expanded = expanded,
        colors = vibrantColors,
        content = content
    )
}