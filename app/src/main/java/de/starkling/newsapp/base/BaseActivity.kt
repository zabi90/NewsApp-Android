package de.starkling.newsapp.base


import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import de.starkling.newsapp.AndroidApp


/**
 * Created by Zohaib Akram on 2019-08-19
 * Copyright © 2019 Starkling. All rights reserved.
 */
abstract class BaseActivity : AppCompatActivity() {


    val Activity.app: AndroidApp
        get() = application as AndroidApp

    val component by lazy {
        app.appComponent
    }


    abstract fun inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }



    fun TextInputLayout.getText(): String {
        return editText?.text.toString()
    }

    fun TextInputLayout.setText(text: String) {
        editText?.setText(text, TextView.BufferType.EDITABLE)
    }


}
