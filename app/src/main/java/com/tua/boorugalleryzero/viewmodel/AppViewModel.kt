package com.tua.boorugalleryzero.viewmodel

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tua.boorugalleryzero.data.persistent.AppDataStore
import com.tua.boorugalleryzero.data.persistent.DataStoreItem
import com.tua.boorugalleryzero.data.persistent.PrefKeys
import com.tua.boorugalleryzero.data.persistent.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppViewModel(
    val context: Context
): ViewModel() {

    val appDataStore = AppDataStore(context)

    val homeSetting = appDataStore.getHomeSetting()
    val gallerySetting = appDataStore.getGallerySetting()

    private fun <T> set(item: DataStoreItem<T>, value: T) =
        viewModelScope.launch {
            context.dataStore.edit { it[item.key] = value }
        }

    fun setViewType(value: Boolean) = set(PrefKeys.VIEW_TYPE,value)

}