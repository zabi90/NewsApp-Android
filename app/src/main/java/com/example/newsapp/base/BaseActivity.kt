package com.example.newsapp.base


import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.AndroidApp
import com.google.android.material.textfield.TextInputLayout




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
