package com.example.testmovieapp.data.model

data class Compilation(
    val id:Int,
    val cast:List<Movie> = listOf()
)