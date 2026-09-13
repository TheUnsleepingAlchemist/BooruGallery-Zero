package com.tua.boorugalleryzero.data.source

import com.tua.boorugalleryzero.domain.Post

interface Client {

    val name: String
    val clientId: Clients
    val baseUrl: String
    val referer: String
    val initPage: Int

    suspend fun fetchPosts(page:Int = initPage) : Result<List<Post>>

}