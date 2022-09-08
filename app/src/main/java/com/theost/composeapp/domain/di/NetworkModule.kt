package com.theost.composeapp.domain.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.theost.composeapp.domain.network.AuthInterceptor
import com.theost.composeapp.domain.network.NetworkConfig.AUTH_API_KEY
import com.theost.composeapp.domain.network.NetworkConfig.BASE_URL
import com.theost.composeapp.domain.network.NetworkConfig.CONNECT_TIMEOUT
import com.theost.composeapp.domain.network.NetworkConfig.READ_TIMEOUT
import com.theost.composeapp.domain.network.NetworkConfig.WRITE_TIMEOUT
import com.theost.composeapp.domain.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit.SECONDS

@Module
class NetworkModule {

    @Provides
    @Reusable
    fun provideJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @Provides
    @Reusable
    fun provideNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Reusable
    @ExperimentalSerializationApi
    fun provideRetrofitClient(httpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Reusable
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(AUTH_API_KEY))
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(CONNECT_TIMEOUT, SECONDS)
            .writeTimeout(WRITE_TIMEOUT, SECONDS)
            .readTimeout(READ_TIMEOUT, SECONDS)
            .build()
    }
}
