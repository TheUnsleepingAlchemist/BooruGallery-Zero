package com.tua.boorugalleryzero.data.source

import com.tua.boorugalleryzero.domain.Post

interface Client {

    val name: String
    val clientId: Clients
    val baseUrl: String
    val referer: String

    suspend fun fetchPosts() : Result<List<Post>>

}