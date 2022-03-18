package com.example.testmovieapp.di

import com.example.testmovieapp.BuildConfig.BASE_URL
import com.example.testmovieapp.core.extentions.addLoggingInterceptor
import com.example.testmovieapp.data.ApiInterface
import com.example.testmovieapp.domain.MainRepository
import com.example.testmovieapp.ui.detail.DetailAdapter
import com.example.testmovieapp.ui.detail.DetailViewModel
import com.example.testmovieapp.ui.popular.PopularAdapter
import com.example.testmovieapp.ui.popular.PopularViewModel
import com.example.testmovieapp.ui.top.TopRatedAdapter
import com.example.testmovieapp.ui.top.TopRatedViewModel
import com.example.testmovieapp.ui.upcoming.UpComingAdapter
import com.example.testmovieapp.ui.upcoming.UpComingViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val timeOut = 50L

val networkModule = module {
    single {
        GsonBuilder().setLenient().create()
    }



    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addLoggingInterceptor(androidApplication().applicationContext)
            .connectTimeout(timeout = timeOut, TimeUnit.SECONDS)
            .readTimeout(timeout = timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeout = timeOut, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()

    }



    single {
        get<Retrofit>().create(ApiInterface::class.java)
    }

}

val repositoryModule = module {
    single { MainRepository(get()) }
}

val viewModelModule = module {
    viewModel { PopularViewModel(get()) }
    viewModel { TopRatedViewModel(get()) }
    viewModel { UpComingViewModel(get()) }
    viewModel { DetailViewModel(get()) }


}

val adapterModule = module {
    single { PopularAdapter() }
    single { TopRatedAdapter() }
    single { UpComingAdapter() }
    single { DetailAdapter() }


}
