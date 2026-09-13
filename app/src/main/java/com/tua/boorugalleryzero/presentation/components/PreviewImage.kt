package com.tua.boorugalleryzero.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest

@Composable
fun PreviewImage(
    modifier: Modifier = Modifier,
    url: String,
    headers: () -> NetworkHeaders
) {

    val model = ImageRequest
        .Builder(LocalContext.current.applicationContext)
        .data(url)
        .httpHeaders(headers())
        .build()

    AsyncImage(
        model,
        null,
        modifier = modifier
            .fillMaxSize()
    )

}