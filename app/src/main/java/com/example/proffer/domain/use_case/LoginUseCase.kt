package com.example.proffer.domain.use_case

import com.example.proffer.data.request.LoginRequest
import com.example.proffer.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(loginRequest: LoginRequest) = repository.login(loginRequest = loginRequest)
}