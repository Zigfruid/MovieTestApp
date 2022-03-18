package com.example.testmovieapp.data.model

import com.google.gson.annotations.SerializedName

data class BiographyOfPerson(
    val id:Int,
    val name:String,
    @SerializedName("biography")
    val biography:String,
    val placeOfBirth:String,
    val popularity:Double,
    @SerializedName("profile_path")
    val profilePath:String
)