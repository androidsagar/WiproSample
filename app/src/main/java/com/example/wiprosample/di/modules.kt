package com.example.wiprosample.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.wiprosample.repository.FeedRepo
import com.example.wiprosample.view_models.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
}

val reposModule = module {
    single { FeedRepo() }
    single { provideGlide(get()) }
}

private fun provideGlide(application: Application):RequestManager{
   return Glide.with(application)
}