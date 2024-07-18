package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class GenreResponse(@SerializedName("data")
                          val genresData: List<Genre>)