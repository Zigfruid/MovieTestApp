package com.example.testmovieapp.data.model

import com.google.gson.annotations.SerializedName

data class Actor(
    val id:Int,
    val name:String,
    @SerializedName("profile_path")
    val profilePath:String?
)
