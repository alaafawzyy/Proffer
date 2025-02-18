package com.example.proffer.domain.use_case

import com.example.proffer.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(token: String) = repository.getMoreAboutUser(token = token)
}