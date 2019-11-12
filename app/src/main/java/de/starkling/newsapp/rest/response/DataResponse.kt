package de.starkling.newsapp.rest.response

import de.starkling.newsapp.models.Article

/**
 * Created by Zohaib Akram on 2019-11-11
 * Copyright © 2019 Starkling. All rights reserved.
 */
data class DataResponse(
    var status: String,
    var totalResults: Int,
    var articles : List<Article>
)