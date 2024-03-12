package com.example.comics.ui

import com.example.comics.data.model.ItemModel

data class UiItemModel(
    var image: String,
    var title: String,
    var subtitle: String
)

fun ItemModel.mapDataModelToUiModel(): List<UiItemModel> = this.data.results.map {
    UiItemModel(
        image = "${it.thumbnail.path}.${it.thumbnail.extension}",
        title = it.title,
        subtitle = it.description ?: ""
    )
}
