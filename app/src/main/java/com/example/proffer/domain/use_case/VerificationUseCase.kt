package com.example.proffer.domain.use_case

import com.example.proffer.data.request.VerificationRequest
import com.example.proffer.data.response.VerificationResponse
import com.example.proffer.domain.repository.VerificationRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.proffer.util.Result

class VerificationUseCase @Inject constructor(
    private val repository: VerificationRepo,
) {
    operator fun invoke(verificationRequest: VerificationRequest):
            Flow<Result<VerificationResponse>> = repository.verify(verificationRequest)


}