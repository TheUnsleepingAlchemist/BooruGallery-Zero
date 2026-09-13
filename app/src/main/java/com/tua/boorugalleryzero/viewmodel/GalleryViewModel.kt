package com.tua.boorugalleryzero.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tua.boorugalleryzero.data.paging.PostPagingSource
import com.tua.boorugalleryzero.data.source.Client
import com.tua.boorugalleryzero.domain.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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

    private val _initialIndex = MutableStateFlow<Int>(0)
    val initialIndex = _initialIndex.asStateFlow()

    fun setInitialIndex(value: Int) {
        _initialIndex.value = value
    }

}