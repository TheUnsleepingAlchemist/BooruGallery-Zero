package com.tua.boorugalleryzero.presentation.components

import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.tua.boorugalleryzero.ui.theme.Icons
import com.tua.boorugalleryzero.ui.theme.IconsFilled

@Composable
fun TextIcon(
    iconName: String,
    modifier: Modifier = Modifier,
    iconSize: TextUnit = IconSize.Small,
    color: Color = Color.Unspecified,
    isFilled: Boolean = false,
) {

    val fontFamily = if (isFilled) IconsFilled else Icons

    Text(
        iconName,
        modifier = modifier,
        fontSize = iconSize,
        fontFamily = fontFamily,
        color = color
    )

}

@Composable
fun TextIconButton(
    iconName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: TextUnit = IconSize.Small,
    color: Color = Color.Unspecified,
    isFilled: Boolean = false,
    enabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        TextIcon(
            iconName,
            iconSize = iconSize,
            color = color,
            isFilled = isFilled
        )
    }
}

sealed interface IconSize {
    companion object {
        val Chip = 18.sp
        val ExtraSmall = 20.sp
        val Small = 24.sp
        val Medium = 24.sp
        val Large = 32.sp
        val ExtraLarge = 40.sp
    }
}