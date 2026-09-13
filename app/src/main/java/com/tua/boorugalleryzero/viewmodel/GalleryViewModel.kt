package com.tua.boorugalleryzero.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tua.boorugalleryzero.data.paging.PostPagingSource
import com.tua.boorugalleryzero.data.source.Client
import com.tua.boorugalleryzero.domain.Post
import kotlinx.coroutines.flow.Flow

class GalleryViewModel(
    val client: Client
) : ViewModel() {

    val postPagingSource: Flow<PagingData<Post>> = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            PostPagingSource(
                client = client
            )
        }
    ).flow

}