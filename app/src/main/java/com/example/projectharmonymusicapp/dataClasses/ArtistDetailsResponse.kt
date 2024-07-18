package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class ArtistDetailsResponse(
    @SerializedName("picture_big") val pictureBig: String,
    @SerializedName("tracklist") val trackList: String)
