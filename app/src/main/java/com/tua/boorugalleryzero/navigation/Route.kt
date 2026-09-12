package com.tua.boorugalleryzero.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface RouteRoot : NavKey {
    @Serializable
    data object HomeScreen: RouteRoot, NavKey
    @Serializable
    data object GalleryScreen: RouteRoot, NavKey
    @Serializable
    data object SettingsScreen: RouteRoot, NavKey
}

@Serializable
sealed interface RouteGallery : NavKey {
    @Serializable
    data object Main: RouteGallery, NavKey
    @Serializable
    data object Preview: RouteGallery, NavKey
    @Serializable
    data object Details: RouteGallery, NavKey
}
@Serializable
sealed interface RouteSettings : NavKey {
    @Serializable
    data object Main: RouteSettings, NavKey
}