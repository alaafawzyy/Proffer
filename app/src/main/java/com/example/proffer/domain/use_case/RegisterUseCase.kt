package com.example.proffer.domain.use_case

import com.example.proffer.data.request.RegisterRequest
import com.example.proffer.data.response.RegisterResponse
import com.example.proffer.domain.repository.RegisterRepository
import com.example.proffer.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    operator fun invoke(request: RegisterRequest): Flow<Result<RegisterResponse>> {
        return repository.register(request)
    }
}
