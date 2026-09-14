package com.tua.boorugalleryzero.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tua.boorugalleryzero.data.source.Client
import com.tua.boorugalleryzero.domain.Post

class PostPagingSource(
    private val client: Client,
    private val fetchQuantity:Int
) : PagingSource<Int, Post>() {
    override fun getRefreshKey(state: PagingState<Int, Post>): Int {
        return client.initPage
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {

        try {
            val key = params.key ?: client.initPage

            val postRes = client.fetchPosts(key, fetchQuantity).getOrNull()!!

            val reachedEnd = postRes.isEmpty()

            return LoadResult.Page(
                data = postRes,
                prevKey = if(key == client.initPage) null else key.minus(1),
                nextKey = if (reachedEnd) null else key.plus(1)
            )

        }
        catch (e: Exception) {
            Log.e("PostPagingSource",e.message?: "")
            return LoadResult.Error(e)
        }

    }
}