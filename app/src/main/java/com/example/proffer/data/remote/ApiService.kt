package com.example.proffer.data.remote

import com.example.proffer.data.request.LoginRequest
import com.example.proffer.data.request.RegisterRequest
import com.example.proffer.data.request.VerificationRequest
import com.example.proffer.data.response.LoginResponse
import com.example.proffer.data.response.ProfileResponse
import com.example.proffer.data.response.RegisterResponse
import com.example.proffer.data.response.VerificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    @POST("auth/verify")
    suspend fun verify(
        @Body verificationRequest: VerificationRequest
    ): Response<VerificationResponse>

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("auth/profile")
    suspend fun getMoreAboutUser(
        @Header("Authorization") token: String,
        @Header("Accept") accept: String
    ): Response<ProfileResponse>
}