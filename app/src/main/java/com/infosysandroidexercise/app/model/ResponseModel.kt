package com.infosysandroidexercise.app.model

import com.google.gson.annotations.SerializedName


data class ResponseModel(
    @SerializedName("rows")
    val rows: List<RowModel>,

    @SerializedName("title")
    val title: String
)