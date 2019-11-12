package de.starkling.selectit.base

import android.view.View

/**
 * Created by Zohaib Akram on 2019-08-19
 * Copyright © 2019 Starkling. All rights reserved.
 */
interface OnItemSelectListener<T> {

    fun onItemSelected(item: T, position: Int, view: View)
}