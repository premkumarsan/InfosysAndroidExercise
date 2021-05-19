package com.infosysandroidexercise.app.model

import com.google.gson.annotations.SerializedName


data class RowModel(
    @SerializedName("description")
    val description: String,

    @SerializedName("imageHref")
    val imageHref: String? = null,

    @SerializedName("title")
    val title: String
)