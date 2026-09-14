package com.tua.boorugalleryzero.data.persistent

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tua.boorugalleryzero.presentation.model.GridSize
import com.tua.boorugalleryzero.presentation.model.ViewType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DATASTORE_NAME = "user_settings"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

data class DataStoreItem<T>(
    val label: String,
    val key: Preferences.Key<T>,
    val defaultValue: T,
    val description: String = "",
)

object PrefKeys {

    val ENABLE_DEVTOOLS = DataStoreItem("Enable Devtools",booleanPreferencesKey("devtools"),false)
    val PROD_API = DataStoreItem("Enable Prod API",booleanPreferencesKey("pro_api"),false)
    val DEMO_MODE = DataStoreItem("Demo mode",booleanPreferencesKey("demo_mode"),true)
    val VIEW_TYPE = DataStoreItem("Change view type",booleanPreferencesKey("view_type"),true)
    val HOME_SIZE_LANDSCAPE = DataStoreItem("Home grid size in landscape",intPreferencesKey("home_grid_size_landscape"),6)
    val HOME_SIZE_PORTRAIT = DataStoreItem("Home grid size in portrait",intPreferencesKey("home_grid_size_portrait"),2)
    val GALLERY_SIZE_LANDSCAPE = DataStoreItem("Gallery grid size in landscape",intPreferencesKey("gallery_grid_size_landscape"),6)
    val GALLERY_SIZE_PORTRAIT = DataStoreItem("Gallery grid size in portrait",intPreferencesKey("gallery_grid_size_portrait"),2)
    val AUTOPLAY_VIDEO = DataStoreItem("Autoplay video",booleanPreferencesKey("autoplay_video"),true)
    val LOOP_VIDEO = DataStoreItem("Loop Video",booleanPreferencesKey("loop_video"),true)
    val MUTE_VIDEO = DataStoreItem("Mute Video",booleanPreferencesKey("mute_video"),true)
    val FETCH_QUANTITY = DataStoreItem("Fetch Quantity",intPreferencesKey("fetch_quantity"),50)
    val PREFETCH_RANGE = DataStoreItem("Prefetch Range",floatPreferencesKey("prefetch_range"),1f)

}

class AppDataStore(val context: Context) {

    fun getHomeSetting(): Flow<HomeSettings> = context.dataStore.data.map {
        HomeSettings(
            viewType = if (it[PrefKeys.VIEW_TYPE.key] ?: PrefKeys.VIEW_TYPE.defaultValue) ViewType.Grid else ViewType.List,
            gridSize = GridSize(
                portrait = it[PrefKeys.HOME_SIZE_PORTRAIT.key]?: PrefKeys.HOME_SIZE_PORTRAIT.defaultValue,
                landscape = it[PrefKeys.HOME_SIZE_LANDSCAPE.key]?: PrefKeys.HOME_SIZE_LANDSCAPE.defaultValue,
            ),
            isLoading = false
        )
    }

    fun getGallerySetting(): Flow<GallerySettings> = context.dataStore.data.map {
        GallerySettings(
            demoMode = it[PrefKeys.DEMO_MODE.key] ?: PrefKeys.DEMO_MODE.defaultValue,
            prodApi = it[PrefKeys.PROD_API.key] ?: PrefKeys.PROD_API.defaultValue,
            gridSize = GridSize(
                portrait = it[PrefKeys.GALLERY_SIZE_PORTRAIT.key]?: PrefKeys.GALLERY_SIZE_PORTRAIT.defaultValue,
                landscape = it[PrefKeys.GALLERY_SIZE_LANDSCAPE.key]?: PrefKeys.GALLERY_SIZE_LANDSCAPE.defaultValue,
            ),
            fetchQuantity = it[PrefKeys.FETCH_QUANTITY.key] ?: PrefKeys.FETCH_QUANTITY.defaultValue,
            prefetchRange = it[PrefKeys.PREFETCH_RANGE.key] ?: PrefKeys.PREFETCH_RANGE.defaultValue,
            autoplayVideo = it[PrefKeys.AUTOPLAY_VIDEO.key] ?: PrefKeys.AUTOPLAY_VIDEO.defaultValue,
            loopVideo = it[PrefKeys.LOOP_VIDEO.key] ?: PrefKeys.LOOP_VIDEO.defaultValue,
            muteVideo = it[PrefKeys.MUTE_VIDEO.key] ?: PrefKeys.MUTE_VIDEO.defaultValue,
        )
    }

    fun getDevSetting(): Flow<DevSettings> = context.dataStore.data.map {
        DevSettings(
            devtools = it[PrefKeys.ENABLE_DEVTOOLS.key] ?: PrefKeys.ENABLE_DEVTOOLS.defaultValue,
            demoMode = it[PrefKeys.DEMO_MODE.key] ?: PrefKeys.DEMO_MODE.defaultValue,
            prodApi = it[PrefKeys.PROD_API.key] ?: PrefKeys.PROD_API.defaultValue,
        )
    }

}

data class HomeSettings(
    val viewType: ViewType,
    val gridSize: GridSize,
    val isLoading: Boolean
)

data class GallerySettings(
    val demoMode: Boolean,
    val prodApi: Boolean,
    val gridSize: GridSize,
    val fetchQuantity: Int,
    val prefetchRange: Float,
    val autoplayVideo: Boolean,
    val loopVideo: Boolean,
    val muteVideo: Boolean,
)

data class DevSettings(
    val devtools: Boolean,
    val demoMode: Boolean,
    val prodApi: Boolean,
)

val defaultHomeSettings = HomeSettings(
    viewType = ViewType.Grid,
    gridSize = GridSize(PrefKeys.HOME_SIZE_PORTRAIT.defaultValue, PrefKeys.HOME_SIZE_LANDSCAPE.defaultValue),
    isLoading = true
)

val defaultGallerySettings = GallerySettings(
    demoMode = PrefKeys.DEMO_MODE.defaultValue,
    prodApi = PrefKeys.PROD_API.defaultValue,
    gridSize = GridSize(PrefKeys.GALLERY_SIZE_PORTRAIT.defaultValue, PrefKeys.GALLERY_SIZE_LANDSCAPE.defaultValue),
    fetchQuantity = PrefKeys.FETCH_QUANTITY.defaultValue,
    prefetchRange = PrefKeys.PREFETCH_RANGE.defaultValue,
    autoplayVideo = PrefKeys.AUTOPLAY_VIDEO.defaultValue,
    loopVideo = PrefKeys.LOOP_VIDEO.defaultValue,
    muteVideo = PrefKeys.MUTE_VIDEO.defaultValue,
)

val defaultDevSettings = DevSettings(
    devtools = false,
    demoMode = true,
    prodApi = false
)