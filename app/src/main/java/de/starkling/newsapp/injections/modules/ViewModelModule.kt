package de.starkling.newsapp.injections.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import de.starkling.newsapp.injections.ViewModelFactory
import de.starkling.newsapp.injections.ViewModelKey
import de.starkling.newsapp.viewmodels.HomeViewModel


/**
 * Created by Zohaib Akram on 2019-08-19
 * Copyright © 2019 Starkling. All rights reserved.
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //Add more ViewModels here
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel



}