package de.starkling.newsapp.models

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
enum class Categories {
    GENERAL,
    BUSINESS,
    SCIENCE,
    TECHNOLOGY,
    HEALTH,
    ENTERTAINMENT,
    SPORTS;

    fun getValue(): String {
        return this.name.toLowerCase()
    }
}
