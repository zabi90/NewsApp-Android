package de.starkling.newsapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
@Parcelize
data class Article(
    var source: Source?,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?
):Parcelable