package de.starkling.newsapp.extensions

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toast(msgId: Int) {
    Toast.makeText(this, getString(msgId), Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(msgId: Int) {
    Toast.makeText(context, getString(msgId), Toast.LENGTH_SHORT).show()
}