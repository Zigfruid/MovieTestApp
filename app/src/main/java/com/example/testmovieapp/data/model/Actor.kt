package com.example.testmovieapp.data.model

import com.google.gson.annotations.SerializedName

data class Actor(
    val name:String,
    @SerializedName("profile_path")
    val profilePath:String?
)
