package de.starkling.newsapp.injections.components

import dagger.Component
import de.starkling.newsapp.fragments.HomeFragment
import de.starkling.newsapp.fragments.SearchFragment
import de.starkling.newsapp.injections.modules.NetworkModule
import de.starkling.newsapp.injections.modules.RepositoryModule
import de.starkling.newsapp.injections.modules.RetrofitModule
import de.starkling.newsapp.injections.modules.ViewModelModule
import javax.inject.Singleton

/**
 * Created by Zohaib Akram on 2019-11-11
 * Copyright © 2019 Starkling. All rights reserved.
 */
@Component(modules = [RetrofitModule::class, NetworkModule::class,ViewModelModule::class,RepositoryModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(searchFragment: SearchFragment)
}