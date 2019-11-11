package de.starkling.newsapp.injections.components

import dagger.Component
import de.starkling.newsapp.MainActivity
import de.starkling.newsapp.injections.modules.NetworkModule
import de.starkling.newsapp.injections.modules.RetrofitModule
import javax.inject.Singleton

/**
 * Created by Zohaib Akram on 2019-11-11
 * Copyright © 2019 Starkling. All rights reserved.
 */
@Component(modules = [RetrofitModule::class, NetworkModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}