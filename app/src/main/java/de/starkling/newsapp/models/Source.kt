package de.starkling.newsapp.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
@Parcelize
data class Source(
    var id: String?,
    var name: String?
):Parcelable