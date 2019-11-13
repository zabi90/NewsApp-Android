package de.starkling.newsapp.injections.components

import dagger.Component
import de.starkling.newsapp.AndroidApp
import de.starkling.newsapp.MainActivity
import de.starkling.newsapp.fragments.HomeFragment
import de.starkling.newsapp.fragments.SearchFragment
import de.starkling.newsapp.injections.modules.*
import javax.inject.Singleton

/**
 * Created by Zohaib Akram on 2019-11-11
 * Copyright © 2019 Starkling. All rights reserved.
 */
@Component(modules = [AppModule::class,RetrofitModule::class, NetworkModule::class,ViewModelModule::class,RepositoryModule::class,DataModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(androidApp: AndroidApp)

    fun inject(homeFragment: HomeFragment)

    fun inject(searchFragment: SearchFragment)

    fun inject(mainActivity: MainActivity)
}