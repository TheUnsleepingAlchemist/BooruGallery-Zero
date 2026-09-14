package com.tua.boorugalleryzero.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class GridSize(
    val portrait:Int,
    val landscape:Int
)
