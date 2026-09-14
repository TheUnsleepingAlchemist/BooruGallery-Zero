package com.tua.boorugalleryzero.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tua.boorugalleryzero.data.paging.PostPagingSource
import com.tua.boorugalleryzero.data.source.Client
import com.tua.boorugalleryzero.domain.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GalleryViewModel(
    val client: Client,
    val fetchQuantity:Int
) : ViewModel() {

    val postPagingSource: Flow<PagingData<Post>> = Pager(
        config = PagingConfig(
            pageSize = fetchQuantity
        ),
        pagingSourceFactory = {
            PostPagingSource(
                client = client,
                fetchQuantity = fetchQuantity
            )
        }
    ).flow.cachedIn(viewModelScope)

    private val _initialIndex = MutableStateFlow<Int>(0)
    val initialIndex = _initialIndex.asStateFlow()

    fun setInitialIndex(value: Int) {
        _initialIndex.value = value
    }

}