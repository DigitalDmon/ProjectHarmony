package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("artist") val artist: Artist?,
    @SerializedName("track") val track: Track?,
    @SerializedName("album") val album: Album?
)
