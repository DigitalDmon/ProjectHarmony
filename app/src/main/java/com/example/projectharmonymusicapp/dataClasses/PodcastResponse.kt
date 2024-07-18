package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class PodcastResponse(@SerializedName("data")
                           val podcastsData: List<Podcast>)
