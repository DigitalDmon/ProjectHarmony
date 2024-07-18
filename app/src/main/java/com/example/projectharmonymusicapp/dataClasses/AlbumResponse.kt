package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class AlbumResponse(@SerializedName("data")
                         val albumsData: List<Album>)
