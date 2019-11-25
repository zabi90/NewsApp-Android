package com.example.newsapp.base

import com.example.newsapp.rest.InvalidAuthException
import com.example.newsapp.rest.response.ApiErrorResponse
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


open class BaseRepository {
    fun onHandleError(error: Throwable): String {

        when (error) {
            is HttpException -> return ApiErrorResponse(error).message
            is SocketTimeoutException -> return "Problem to connect server!"

            is IOException -> return "You are offline. Please connect to internet."

            is InvalidAuthException -> return error.message?:"Invalid credentials"
        }

        return "Unknown error"
    }
}