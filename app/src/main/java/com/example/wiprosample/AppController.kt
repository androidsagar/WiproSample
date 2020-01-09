package com.example.wiprosample

import android.app.Application
import com.example.wiprosample.di.networkRequestModule
import com.example.wiprosample.di.reposModule
import com.example.wiprosample.di.retrofitModule
import com.example.wiprosample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppController)
            modules(listOf(retrofitModule, networkRequestModule, viewModelModule, reposModule))
        }
    }
}