package com.example.testmovieapp.domain

import com.example.testmovieapp.data.ApiInterface
import com.example.testmovieapp.data.model.BiographyOfPerson
import com.example.testmovieapp.data.model.Compilation
import com.example.testmovieapp.data.model.Credit
import com.example.testmovieapp.data.model.ResponseMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository(private val api:ApiInterface) {

    val API_KEY="c1516515123cbdece10be2ed99879c53"
   // val SESSION_ID="d2e82d1c0dc65178821cd029af47f168a38f9e72"

    fun getPopularMovies(): Flow<Result<ResponseMovies>> = flow{
        val response=api.getPopularMovies(API_KEY)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!))
        }
        else{
            emit(Result.failure(Throwable("Произошла ошибка")))
        }
    }.flowOn(Dispatchers.IO)


    fun getTopRatedMovies(): Flow<Result<ResponseMovies>> = flow{
        val response=api.getTopRatedMovies(API_KEY)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!))
        }
        else{
            emit(Result.failure(Throwable("Произошла ошибка")))
        }
    }.flowOn(Dispatchers.IO)


    fun getUpcomingMovies(): Flow<Result<ResponseMovies>> = flow{
        val response=api.getUpcomingMovies(API_KEY)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!))
        }
        else{
            emit(Result.failure(Throwable("Произошла ошибка")))
        }
    }.flowOn(Dispatchers.IO)

    fun getMovieCredits(movieId:Int):Flow<Result<Credit>> =flow{
        val response=api.getMovieCredits(movieId,API_KEY)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!))
        }
        else{
            emit(Result.failure(Throwable()))
        }
    }.flowOn(Dispatchers.IO)


    fun getBio(actorId:Int):Flow<Result<BiographyOfPerson>> = flow {
        val response = api.getPersonInfo(actorId, API_KEY)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!))
        }else{
            emit(Result.failure(Throwable()))
        }
    }.flowOn(Dispatchers.IO)

    fun getMovies(actorId:Int):Flow<Result<Compilation>> = flow {
        val response = api.getMovieByActor(actorId, API_KEY)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!))
        }else{
            emit(Result.failure(Throwable()))
        }
    }.flowOn(Dispatchers.IO)


}