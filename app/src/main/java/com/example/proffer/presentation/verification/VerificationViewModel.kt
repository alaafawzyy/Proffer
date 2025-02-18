package com.example.proffer.presentation.verification

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proffer.data.request.VerificationRequest
import com.example.proffer.data.response.VerificationResponse
import com.example.proffer.domain.use_case.VerificationUseCase
import com.example.proffer.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val verificationUseCase: VerificationUseCase
) : ViewModel() {

    private val _verificationState = MutableStateFlow<Result<VerificationResponse>>(Result.Loading())
    val verificationState: StateFlow<Result<VerificationResponse>> get() = _verificationState

    fun verification(verificationRequest: VerificationRequest) {
        viewModelScope.launch {
            verificationUseCase.invoke(verificationRequest)
                .catch { e ->
                    Log.e("VerificationError", "API call failed", e)
                    _verificationState.value = Result.Error("Unexpected Error: ${e.message}")
                }
                .collectLatest { result ->
                    _verificationState.value = result
                }
        }
    }
}