package com.example.proffer.presentation.log_in

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proffer.data.data_store.DataStoreManager
import com.example.proffer.data.request.LoginRequest
import com.example.proffer.data.response.LoginResponse
import com.example.proffer.domain.use_case.LoginUseCase
import com.example.proffer.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataStoreManager: DataStoreManager
): ViewModel() {
    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Loading())
    val loginState: MutableStateFlow<Result<LoginResponse>> get() = _loginState

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginUseCase.invoke(loginRequest)
                .catch { e ->
                    Log.e("LoginError", "API call failed", e)
                    _loginState.value = Result.Error("Unexpected Error: ${e.message}")
                }
                .collectLatest { result ->
                    _loginState.value = result
                    if (result is Result.Success) {
                        result.data?.token?.let { token ->
                            Log.d("New Token", token)
                            dataStoreManager.saveAuthToken(token)
                        }
                    }
                }
        }
    }

    fun getAuthToken(): Flow<String?> = dataStoreManager.getToken

}