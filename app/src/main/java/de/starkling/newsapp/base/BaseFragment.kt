package de.starkling.newsapp.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import de.starkling.newsapp.AndroidApp

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
abstract class BaseFragment : Fragment() {

    val Fragment.app: AndroidApp
        get() = activity?.application as AndroidApp

    val component by lazy {
        app.appComponent
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    abstract fun inject()
}