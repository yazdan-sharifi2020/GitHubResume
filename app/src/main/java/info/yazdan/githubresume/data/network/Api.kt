package info.yazdan.githubresume.data.network

import info.yazdan.githubresume.API_BASE_URL
import info.yazdan.githubresume.data.network.response.GithubUserResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

const val NETWORK_PROCESS_TIMEOUT = 15L

interface Api {

    @GET("users/{username}")
    suspend fun fetchUsername(@Path("username") username: String): Response<GithubUserResponse>

    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : Api {

            val apiKeyInterceptor = Interceptor { chain ->
                val headers = chain
                    .request()
                    .newBuilder()
                    .header("Content-Type", "application/json")
                    .header("charset", "UTF-8")
                    .build()
                return@Interceptor chain.proceed(headers)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addNetworkInterceptor(apiKeyInterceptor)
                .connectTimeout(NETWORK_PROCESS_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(NETWORK_PROCESS_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_PROCESS_TIMEOUT, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }

}