package com.tua.boorugalleryzero.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FetchQuery(
    val tags: String,
    val page: Int,
    val limit: Int
)
