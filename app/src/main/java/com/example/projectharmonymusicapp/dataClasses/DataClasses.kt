package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class Albums(
    val id: Long,
    val title: String,
    val cover: String
)

data class AlbumsResponse(
    val albums: List<Albums>
)

data class GenresResponse(
    @SerializedName("data")
    val genres: List<Genres>
)

data class Genres(
    val id: Long,
    val name: String,
    val picture: String
)
