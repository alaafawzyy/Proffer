package com.example.proffer.domain.repository

import com.example.proffer.data.request.RegisterRequest
import com.example.proffer.data.response.RegisterResponse
import com.example.proffer.util.Result
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun register(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>>
}