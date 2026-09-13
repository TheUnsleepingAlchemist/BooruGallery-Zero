package com.tua.boorugalleryzero.domain

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val md5: String,
    val tags: String,
    val fileExt: String,
    val fileUrl: String,
    val previewUrl: String,
    val sampleUrl: String,
    val createdAt: String,
    val width: Int,
    val height: Int,
    val parentId: Int?,
    val rating: Rating,
    val fileType: FileType
)
