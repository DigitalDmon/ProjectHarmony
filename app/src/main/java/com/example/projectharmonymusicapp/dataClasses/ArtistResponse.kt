package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class ArtistResponse(@SerializedName("data")
                          val artistsData: List<Artist>)
