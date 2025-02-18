package com.example.proffer.domain.repository

import com.example.proffer.data.request.LoginRequest
import com.example.proffer.data.response.LoginResponse
import com.example.proffer.util.Result
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(loginRequest: LoginRequest): Flow<Result<LoginResponse>>
}