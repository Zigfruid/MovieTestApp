package com.example.testmovieapp.data

import com.example.testmovieapp.data.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("account/{account_id}/movie/recommendations")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey:String,
        @Query("session_id") sessionId:String
    ): Response<ResponseMovies>

}