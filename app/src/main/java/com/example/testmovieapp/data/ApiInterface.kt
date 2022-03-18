package com.example.testmovieapp.data

import com.example.testmovieapp.data.model.BiographyOfPerson
import com.example.testmovieapp.data.model.Compilation
import com.example.testmovieapp.data.model.Credit
import com.example.testmovieapp.data.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey:String
    ): Response<ResponseMovies>


    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey:String
    ): Response<ResponseMovies>


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey:String
    ): Response<ResponseMovies>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId:Int,
        @Query("api_key") apiKey:String
    ):Response<Credit>

    @GET("person/{person_id}")
    suspend fun getPersonInfo(
        @Path("person_id") actorId:Int,
        @Query("api_key") apiKey: String
    ):Response<BiographyOfPerson>

    @GET("person/{person_id}/movie_credits")
    suspend fun getMovieByActor(
        @Path("person_id") actorId: Int,
        @Query("api_key") apiKey: String
    ):Response<Compilation>


}