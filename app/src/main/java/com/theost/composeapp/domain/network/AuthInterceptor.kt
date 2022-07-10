package com.theost.composeapp.domain.network

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val authenticatedRequest = chain.request().newBuilder()
            .header("X-API-KEY", apiKey)
            .build()
        return chain.proceed(authenticatedRequest)
    }
}
