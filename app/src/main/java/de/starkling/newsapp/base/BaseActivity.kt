package de.starkling.newsapp.base


import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
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


    fun Activity.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Activity.toast(msgId: Int) {
        Toast.makeText(this, getString(msgId), Toast.LENGTH_SHORT).show()
    }


    fun TextInputLayout.getText(): String {
        return editText?.text.toString()
    }

    fun TextInputLayout.setText(text: String) {
        editText?.setText(text, TextView.BufferType.EDITABLE)
    }

    fun showSnackBar(restId: Int, color: Int = Color.BLACK) {
        val snackBar = Snackbar.make(window.decorView.rootView, restId, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(color)
        snackBar.show()
    }

}
