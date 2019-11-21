package com.example.newsapp

import android.app.Application
import com.example.newsapp.injections.components.DaggerApplicationComponent
import com.example.newsapp.injections.modules.AppModule



class AndroidApp : Application() {
    val appComponent = DaggerApplicationComponent.builder().appModule(AppModule(this)).build()
}