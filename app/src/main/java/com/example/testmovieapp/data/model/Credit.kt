package com.example.testmovieapp.data.model

data class Credit(
    val id:Int,
    val cast:List<Actor> = listOf()
)
