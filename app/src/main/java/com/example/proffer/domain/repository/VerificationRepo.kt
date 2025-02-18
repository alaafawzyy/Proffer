package com.example.proffer.domain.repository

import com.example.proffer.data.request.VerificationRequest
import com.example.proffer.data.response.VerificationResponse
import kotlinx.coroutines.flow.Flow
import com.example.proffer.util.Result

interface VerificationRepo {
    fun verify(verificationRequest: VerificationRequest): Flow<Result<VerificationResponse>>
}