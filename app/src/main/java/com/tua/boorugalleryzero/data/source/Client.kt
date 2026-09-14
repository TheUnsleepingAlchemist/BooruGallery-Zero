package com.tua.boorugalleryzero.data.source

import com.tua.boorugalleryzero.data.model.FetchQuery
import com.tua.boorugalleryzero.domain.Post

interface Client {

    val name: String
    val clientId: Clients
    val baseUrl: String
    val referer: String
    val initPage: Int

    suspend fun fetchPosts(fetchQuery: FetchQuery) : Result<List<Post>>

}

