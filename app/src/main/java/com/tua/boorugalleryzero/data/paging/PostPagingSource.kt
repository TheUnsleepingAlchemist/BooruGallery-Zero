package com.tua.boorugalleryzero.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tua.boorugalleryzero.data.model.FetchQuery
import com.tua.boorugalleryzero.data.source.Client
import com.tua.boorugalleryzero.domain.Post

class PostPagingSource(
    private val client: Client,
    private val fetchQuery: FetchQuery,
    private val tags: String
) : PagingSource<FetchQuery, Post>() {
    override fun getRefreshKey(state: PagingState<FetchQuery, Post>): FetchQuery {
        return FetchQuery(
            tags = tags,
            page = client.initPage,
            limit = fetchQuery.limit
        )
    }

    override suspend fun load(params: LoadParams<FetchQuery>): LoadResult<FetchQuery, Post> {

        try {
            val key = params.key ?: FetchQuery(
                tags = tags,
                page = client.initPage,
                limit = fetchQuery.limit
            )

            val postRes = client.fetchPosts(key).getOrNull()!!

            val reachedEnd = postRes.isEmpty()

            return LoadResult.Page(
                data = postRes,
                prevKey = if(key.page == client.initPage) null else key.copy(page = key.page.minus(1)),
                nextKey = if (reachedEnd) null else key.copy(page = key.page.plus(1))
            )

        }
        catch (e: Exception) {
            Log.e("PostPagingSource",e.message?: "")
            return LoadResult.Error(e)
        }

    }
}