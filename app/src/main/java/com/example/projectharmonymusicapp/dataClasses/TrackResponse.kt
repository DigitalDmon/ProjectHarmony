package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class TrackResponse(@SerializedName("data")
                         val tracksData: List<Track>)

