package de.starkling.newsapp.models

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
data class Article(
    var source: Source?,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?
)