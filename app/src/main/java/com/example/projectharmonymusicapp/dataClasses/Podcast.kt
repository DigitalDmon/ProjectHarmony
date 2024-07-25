package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Podcast(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("picture_big") val pictureBig: String
)
