package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("cover_medium") val coverMedium: String
)
