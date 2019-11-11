package de.starkling.newsapp.injections.modules


import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import de.starkling.newsapp_android.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class RetrofitModule {

    companion object {
        const val PUBLIC_CLIENT = "publicClient"
        const val AUTH_CLIENT = "authClient"
    }

    private val gson = GsonBuilder()
        .create()

    @Singleton
    @Provides
    @Named(PUBLIC_CLIENT)
    fun getPublicRetrofitClient(): Retrofit {


        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor { chain: Interceptor.Chain ->

                val request: Request = chain.request()
                    .newBuilder()
                    .build()

                chain.proceed(request)
            }


        if (BuildConfig.DEBUG) {
            client.addInterceptor(logging)
        }
        client.connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client.build())
            .build()

    }


    @Singleton
    @Provides
    @Named(AUTH_CLIENT)
    fun getAuthRetrofitClient(): Retrofit {

        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY


        val client = OkHttpClient.Builder()

        client.addInterceptor { chain: Interceptor.Chain ->

            val token = "d0fccc72c53845a9b26105a0d8c9abbe"


            val request: Request = chain.request()
                .newBuilder()
               // .addHeader("Authorization", token)
                .build()

            chain.proceed(request)
        }


        if (BuildConfig.DEBUG) {
            client.addInterceptor(logging)
        }
        client.connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)


        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client.build())
            .build()

    }


}