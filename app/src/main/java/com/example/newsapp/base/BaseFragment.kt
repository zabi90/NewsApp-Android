package com.example.newsapp.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newsapp.AndroidApp


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