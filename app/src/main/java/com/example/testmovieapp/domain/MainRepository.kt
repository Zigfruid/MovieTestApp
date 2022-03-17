package com.example.testmovieapp.domain

import com.example.testmovieapp.data.ApiInterface
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

}