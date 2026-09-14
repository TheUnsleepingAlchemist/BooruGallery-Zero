package com.tua.boorugalleryzero.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun BottomSearch(
    state: TextFieldState,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {

    val isEmpty by remember {
        derivedStateOf {
            state.text.isEmpty()
        }
    }

    BasicTextField(
        state = state,
        modifier = modifier
            .height(48.dp)
            .clip(FloatingToolbarDefaults.ContainerShape)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .focusable(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        onKeyboardAction = {
            onSearch()
        },
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = {

            Row(
                modifier = Modifier.widthIn(max = 500.dp).fillMaxWidth(0.75f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp, end = 8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    it()
                    if (isEmpty) {
                        Text("Search...")
                    }
                }
                if (!isEmpty) {
                    TextIconButton(
                        iconName = "close",
                        onClick =  {
                            state.clearText()
                        }
                    )
                }
            }
        }
    )
}