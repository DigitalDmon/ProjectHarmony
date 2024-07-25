package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("cover_big") val coverBig: String
)
