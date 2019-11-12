package de.starkling.newsapp.rest.response


import com.google.gson.JsonParser
import retrofit2.HttpException

class ApiErrorResponse constructor(error: Throwable) {
    var message = "An error occurred"
    var code = "code"

    init {
        if (error is HttpException) {
            if(error.code() == 404){
                this.message = "url not found"
            }else{

                try {

                    val errorJsonString = error.response()
                        ?.errorBody()?.string()

                    this.message = JsonParser().parse(errorJsonString)
                        .asJsonObject["message"]
                        .asString

                    this.code = JsonParser().parse(errorJsonString)
                        .asJsonObject["code"]
                        .asString

                }catch (exception:Exception){
                    this.message = exception.message ?: this.message
                }

            }


        } else {
            this.message = error.message ?: this.message
        }
    }
}