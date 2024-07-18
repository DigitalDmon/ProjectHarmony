package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName
import java.util.Date

data class AlbumDetailsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("release_date") val releaseDate: Date,
    @SerializedName("artist") val artist: Artist,
    @SerializedName("tracks") val tracks: List<Track>
)
