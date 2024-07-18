package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class SearchResponse(@SerializedName("data")
                          val searchData: List<Search>)
