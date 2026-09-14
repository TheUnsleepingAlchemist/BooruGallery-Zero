package com.tua.boorugalleryzero.navigation

import androidx.navigation3.runtime.NavKey
import com.tua.boorugalleryzero.domain.Post
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
    data class Details(val post: Post): RouteGallery, NavKey
}

@Serializable
sealed interface RouteSettings : NavKey {
    @Serializable
    data object Main: RouteSettings, NavKey
    @Serializable
    data object Home: RouteSettings, NavKey
    @Serializable
    data object Gallery: RouteSettings, NavKey
    @Serializable
    data object Dev: RouteSettings, NavKey
    @Serializable
    data object About: RouteSettings, NavKey
}