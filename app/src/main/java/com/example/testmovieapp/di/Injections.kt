package com.example.testmovieapp.di

import com.example.testmovieapp.data.ApiInterface
import com.example.testmovieapp.ui.popular.PopularAdapter
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val baseUrl = "https://api.themoviedb.org/3/"
private const val timeOut = 50L

val networkModule = module {
    single {
        GsonBuilder().setLenient().create()
    }



//    single {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addLoggingInterceptor(androidApplication().applicationContext)
//            .connectTimeout(timeout = timeOut, TimeUnit.SECONDS)
//            .readTimeout(timeout = timeOut, TimeUnit.SECONDS)
//            .writeTimeout(timeout = timeOut, TimeUnit.SECONDS)
//            .retryOnConnectionFailure(true)
//            .build()
//    }
//
//    single {
//        Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .client(get())
//            .build()
//
//    }



    single {
        get<Retrofit>().create(ApiInterface::class.java)
    }

}

val repositoryModule = module {
   // single { MainRepository(get()) }
}

val viewModelModule = module {
//    viewModel { MainViewModel(get()) }
//    viewModel { MovieViewModel(get()) }
//    viewModel { FavoriteViewModel(get()) }
}

val adapterModule = module {
    single { PopularAdapter() }

}
