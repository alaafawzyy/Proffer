package com.example.proffer.data.repository

import android.util.Log
import com.example.proffer.data.remote.ApiService
import com.example.proffer.data.request.RegisterRequest
import com.example.proffer.data.response.RegisterResponse
import com.example.proffer.domain.repository.RegisterRepository
import com.example.proffer.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterRepoImpl @Inject constructor(
    private val apiService: ApiService
) : RegisterRepository {

    override fun register(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>> = flow {
        emit(Result.Loading())

        try {
            val response = apiService.register(registerRequest)

            if (response.isSuccessful) {
                Log.d("RegisterRepoImpl", "API call successful")
                response.body()?.let {
                    emit(Result.Success(it))
                } ?: emit(Result.Error("Empty response body"))

            } else {
                Log.d("RegisterRepoImpl", "API call failed")
                emit(Result.Error("Error: ${response.errorBody()?.string()}"))
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
