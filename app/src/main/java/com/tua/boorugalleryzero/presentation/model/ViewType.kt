package com.tua.boorugalleryzero.presentation.model

enum class ViewType {
    Grid,List;

    companion object {
        fun fromBoolean(value: Boolean): ViewType = when(value) {
            true -> Grid
            false -> List
        }
        fun toBoolean(value: ViewType): Boolean = when(value) {
            Grid -> true
            List -> false
        }
    }
}