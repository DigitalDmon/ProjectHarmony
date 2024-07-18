package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("picture_medium") val pictureMedium: String
)
