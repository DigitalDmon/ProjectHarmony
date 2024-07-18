package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("picture_medium") val pictureMedium: String,
)
