package com.example.proffer.domain.repository

import com.example.proffer.data.response.ProfileResponse
import kotlinx.coroutines.flow.Flow
import com.example.proffer.util.Result


interface ProfileRepository {
    fun getMoreAboutUser(token: String): Flow<Result<ProfileResponse>>
}