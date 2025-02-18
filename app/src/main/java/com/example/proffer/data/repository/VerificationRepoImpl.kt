package com.example.proffer.data.repository

import android.util.Log
import com.example.proffer.data.remote.ApiService
import com.example.proffer.data.request.VerificationRequest
import com.example.proffer.data.response.VerificationResponse
import com.example.proffer.domain.repository.VerificationRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import com.example.proffer.util.Result
import java.io.IOException
import javax.inject.Inject

class VerificationRepoImpl @Inject constructor(
    private val apiService: ApiService
): VerificationRepo {
    override fun verify(verificationRequest: VerificationRequest): Flow<Result<VerificationResponse>> =
        flow {
            emit(Result.Loading())

            try {
                val response = apiService.verify(verificationRequest)

                if (response.isSuccessful) {
                    Log.d("VerificationRepoImpl", "API call successful")
                    response.body()?.let {
                        emit(Result.Success(it))
                    } ?: emit(Result.Error("Empty response body"))

                } else {
                    Log.d("RegisterRepoImpl", "API call failed")
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