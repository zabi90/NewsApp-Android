package com.example.newsapp.rest

/**
 * Exception for authentication
 */
class InvalidAuthException(override val message: String?) :Throwable(message)