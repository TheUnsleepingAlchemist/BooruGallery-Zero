package com.tua.boorugalleryzero.presentation.screens.gallery

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tua.boorugalleryzero.domain.Post
import com.tua.boorugalleryzero.presentation.components.TextIcon
import com.tua.boorugalleryzero.presentation.components.TextIconButton
import kotlinx.serialization.Serializable

@Composable
fun GalleryDetailsScreen(
    modifier: Modifier = Modifier,
    post: Post,
    onBackClick: () -> Unit
) {

    val items = listOf(
        DetailsItem("Id",post.id.toString(),"123"),
        DetailsItem("Md5",post.md5,"fingerprint"),
        DetailsItem("Tags",post.tags,"tag"),
        DetailsItem("Width",post.width.toString(),"arrows_left_right_circle"),
        DetailsItem("Height",post.height.toString(),"arrows_up_down_circle"),
        DetailsItem("Rating",post.rating.name,"category"),
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Details")
                },
                navigationIcon = {
                    TextIconButton(
                        iconName = "arrow_back",
                        onClick = onBackClick
                    )
                },
            )
        }
    ) { sPadding ->

        LazyColumn(
            modifier = Modifier.padding(sPadding)
        ) {

            itemsIndexed(
                items = items,
                key = { _, item -> item.label }
            ) { i, item ->

                SegmentedListItem(
                    shapes = ListItemDefaults.segmentedShapes(i,items.size),
                    leadingContent = {
                        TextIcon(item.iconName)
                    },
                    supportingContent = {
                        Text(item.description)
                    }
                ) {
                    Text(item.label)
                }

            }

        }

    }
}

@Serializable
data class DetailsItem(
    val label: String,
    val description: String,
    val iconName: String
)