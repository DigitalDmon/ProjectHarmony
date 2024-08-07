package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("album") val album: Album,
    @SerializedName("duration") val duration: Int,
    @SerializedName("preview") val preview: String
)
