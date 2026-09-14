package com.tua.boorugalleryzero.viewmodel

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tua.boorugalleryzero.data.persistent.AppDataStore
import com.tua.boorugalleryzero.data.persistent.DataStoreItem
import com.tua.boorugalleryzero.data.persistent.PrefKeys
import com.tua.boorugalleryzero.data.persistent.dataStore
import kotlinx.coroutines.launch

class AppViewModel(
    val context: Context
): ViewModel() {

    val appDataStore = AppDataStore(context)

    val homeSetting = appDataStore.getHomeSetting()
    val gallerySetting = appDataStore.getGallerySetting()
    val devSettings = appDataStore.getDevSetting()

    private fun <T> set(item: DataStoreItem<T>, value: T) =
        viewModelScope.launch {
            context.dataStore.edit { it[item.key] = value }
        }

    fun setViewType(value: Boolean) = set(PrefKeys.VIEW_TYPE,value)
    fun setHomeSizePortrait(value: Int) = set(PrefKeys.HOME_SIZE_PORTRAIT,value)
    fun setHomeSizeLandscape(value: Int) = set(PrefKeys.HOME_SIZE_LANDSCAPE,value)

    fun setGallerySizePortrait(value: Int) = set(PrefKeys.GALLERY_SIZE_PORTRAIT,value)
    fun setGallerySizeLandscape(value: Int) = set(PrefKeys.GALLERY_SIZE_LANDSCAPE,value)
    fun setFetchQuantity(value: Int) = set(PrefKeys.FETCH_QUANTITY,value)
    fun setAutoplayVideo(value: Boolean) = set(PrefKeys.AUTOPLAY_VIDEO,value)
    fun setLoopVideo(value: Boolean) = set(PrefKeys.LOOP_VIDEO,value)
    fun setMuteVideo(value: Boolean) = set(PrefKeys.MUTE_VIDEO,value)


    fun setDevtools(value: Boolean) = set(PrefKeys.ENABLE_DEVTOOLS,value)
    fun setDemoMode(value: Boolean) = set(PrefKeys.DEMO_MODE,value)

}