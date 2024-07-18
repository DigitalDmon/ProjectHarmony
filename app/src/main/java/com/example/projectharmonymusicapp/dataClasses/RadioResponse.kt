package com.example.projectharmonymusicapp.dataClasses

import com.google.gson.annotations.SerializedName

data class RadioResponse(@SerializedName("data")
                         val radioData: List<Radio>)
