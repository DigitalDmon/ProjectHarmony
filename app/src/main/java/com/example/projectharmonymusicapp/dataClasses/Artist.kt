package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("picture_big") val pictureBig: String
)
