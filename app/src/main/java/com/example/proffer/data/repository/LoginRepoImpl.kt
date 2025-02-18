package com.example.proffer.data.repository

import android.util.Log
import com.example.proffer.data.remote.ApiService
import com.example.proffer.data.request.LoginRequest
import com.example.proffer.data.response.LoginResponse
import com.example.proffer.domain.repository.LoginRepository
import com.example.proffer.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(
    private val apiService: ApiService
): LoginRepository {
    override fun login(loginRequest: LoginRequest): Flow<Result<LoginResponse>> = flow {
        emit(Result.Loading())

        try {
            val response = apiService.login(loginRequest)

            if (response.isSuccessful) {
                Log.d("LoginRepoImpl", "login API call successful")
                response.body()?.let {
                    emit(Result.Success(it))
                } ?: emit(Result.Error("Empty response body"))

            } else {
                Log.d("LoginRepoImpl", "login API call failed")
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