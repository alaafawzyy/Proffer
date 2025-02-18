package com.example.proffer.presentation.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proffer.data.request.RegisterRequest
import com.example.proffer.data.response.RegisterResponse
import com.example.proffer.domain.use_case.RegisterUseCase
import com.example.proffer.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow<Result<RegisterResponse>>(Result.Loading())
    val registerState: StateFlow<Result<RegisterResponse>> get() = _registerState

    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            registerUseCase.invoke(registerRequest)
                .catch { e ->
                    Log.e("RegisterError", "API call failed", e)
                    _registerState.value = Result.Error("Unexpected Error: ${e.message}")
                }
                .collectLatest { result ->
                    _registerState.value = result
                }
        }
    }
}
