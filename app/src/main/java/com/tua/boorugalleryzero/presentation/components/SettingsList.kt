package com.tua.boorugalleryzero.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

@Composable
fun SettingsList(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    TextIconButton(
                        iconName = "arrow_back",
                        onClick = onClick
                    )
                },
                title = {
                    Text(label)
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { scaffoldPadding ->
        LazyColumn(
            modifier = Modifier.padding(scaffoldPadding),
            verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
            contentPadding = PaddingValues(8.dp),
            content = content
        )
    }
}