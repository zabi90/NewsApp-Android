package de.starkling.newsapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */

@Parcelize
@Entity
data class Article(
    @Embedded val source: Source?,
    val author: String?,
    @PrimaryKey val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    @ColumnInfo(name = "published_at") val publishedAt: String?,
    val content: String?,
    @ColumnInfo(defaultValue = "general") var category: String?
) : Parcelable