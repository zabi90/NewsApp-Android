package de.starkling.newsapp.base

import androidx.lifecycle.ViewModel
import de.starkling.newsapp.rest.InvalidAuthException
import de.starkling.newsapp.rest.response.ApiErrorResponse
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
open class BaseViewModel: ViewModel() {

    fun onHandleError(error: Throwable): String {

        when (error) {
            is HttpException -> return ApiErrorResponse(error).message
            is SocketTimeoutException -> return "Problem to connect server!"
            is IOException -> return "Problem to internet connection!"
            is InvalidAuthException -> return error.message?:"Invalid credentials"
        }

        return "Unknown error"
    }
}