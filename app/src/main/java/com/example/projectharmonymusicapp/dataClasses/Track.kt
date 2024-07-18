package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("artist") val artist: Artist,
    @SerializedName("album") val album: Album,
    @SerializedName("picture_medium") val pictureMedium: String
)
