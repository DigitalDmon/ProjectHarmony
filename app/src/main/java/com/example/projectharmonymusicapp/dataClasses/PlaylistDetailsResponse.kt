package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class PlaylistDetailsResponse(
    @SerializedName("data") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("picture_medium") val pictureMedium: String,
    @SerializedName("tracks") val tracks: List<Track>)
