package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class TrackDetailsResponse(
    @SerializedName("artist") val artist: Artist,
    @SerializedName("album") val album: Album,
    @SerializedName("duration") val duration: Int,
    @SerializedName("preview") val preview: String
)
