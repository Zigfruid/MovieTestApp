package com.example.testmovieapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.testmovieapp.di.adapterModule
import com.example.testmovieapp.di.networkModule
import com.example.testmovieapp.di.repositoryModule
import com.example.testmovieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val modules = listOf(networkModule,viewModelModule, adapterModule, repositoryModule)
        startKoin {
            androidLogger()

            androidContext(this@App)

            androidFileProperties()

            koin.loadModules(modules)
        }
    }
}