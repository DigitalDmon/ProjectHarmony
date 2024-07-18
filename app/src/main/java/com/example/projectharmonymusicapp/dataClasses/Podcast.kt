package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Podcast(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("picture_medium") val pictureMedium: String
)
