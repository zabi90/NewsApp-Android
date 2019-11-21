package com.example.newsapp.models


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
