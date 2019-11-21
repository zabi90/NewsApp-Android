package com.example.newsapp.injections.components

import com.example.newsapp.AndroidApp
import com.example.newsapp.MainActivity
import com.example.newsapp.fragments.HomeFragment
import com.example.newsapp.fragments.SearchFragment
import com.example.newsapp.injections.modules.*
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class, RetrofitModule::class, NetworkModule::class, ViewModelModule::class, RepositoryModule::class,DataModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(androidApp: AndroidApp)

    fun inject(homeFragment: HomeFragment)

    fun inject(searchFragment: SearchFragment)

    fun inject(mainActivity: MainActivity)
}