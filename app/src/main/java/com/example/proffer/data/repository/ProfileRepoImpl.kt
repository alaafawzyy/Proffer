package com.example.proffer.data.repository

import android.util.Log
import com.example.proffer.data.remote.ApiService
import com.example.proffer.data.response.ProfileResponse
import com.example.proffer.domain.repository.ProfileRepository
import com.example.proffer.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProfileRepoImpl @Inject constructor(
    private val apiService: ApiService
): ProfileRepository {
    override fun getMoreAboutUser(token: String): Flow<Result<ProfileResponse>> = flow {
        emit(Result.Loading())

        try {
            val response = apiService.getMoreAboutUser(token, "application/json")

            if (response.isSuccessful) {
                Log.d("profileRepoImpl", "successful")
                response.body()?.let {
                    emit(Result.Success(it))
                } ?: emit(Result.Error("Empty response body"))

            } else {
                Log.d("profileRepoImpl", "profile API call failed")
                emit(
                    Result.Error(
                        "Error: ${
                            response.errorBody()?.string()
                        }"
                    )
                )
            }

        } catch (e: HttpException) {
            emit(Result.Error("HTTP Error: ${e.message}"))
        } catch (e: IOException) {
            emit(Result.Error("Network Error: ${e.message}"))
        } catch (e: Exception) {
            emit(Result.Error("Unexpected Error: ${e.message}"))
        }
    }.flowOn(Dispatchers.IO)

}