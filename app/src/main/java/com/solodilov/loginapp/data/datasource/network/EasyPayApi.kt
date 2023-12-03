package com.solodilov.loginapp.data.datasource.network

import com.solodilov.loginapp.data.model.Auth
import com.solodilov.loginapp.data.model.AuthResponse
import com.solodilov.loginapp.data.model.PaymentListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EasyPayApi {

    @POST("login")
    suspend fun login(@Body auth: Auth): AuthResponse

    @GET("payments")
    suspend fun getPayments(): PaymentListResponse
}