package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class PlaylistResponse(@SerializedName("data")
                            val playlistsData: List<Playlist>)
