package com.example.proffer.presentation.navigation.navbar_screens.more

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proffer.data.data_store.DataStoreManager
import com.example.proffer.data.response.ProfileResponse
import com.example.proffer.domain.use_case.ProfileUseCase
import com.example.proffer.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoreViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _profileState = MutableStateFlow<Result<ProfileResponse>>(Result.Loading())
    val profileState: MutableStateFlow<Result<ProfileResponse>> get() = _profileState

    fun getMoreAboutUser(token: String) {  // Accepts token parameter
        viewModelScope.launch {
            try {
                if (token.isNotEmpty()) {
                    profileUseCase.invoke(token) // Pass token
                        .catch { e ->
                            Log.e("ProfileRequestError", "API call failed", e)
                            _profileState.value = Result.Error("Unexpected Error: ${e.message}")
                        }
                        .collectLatest { result ->
                            _profileState.value = result
                        }
                } else {
                    Log.e("ProfileRequestError", "Token is missing!")
                    _profileState.value = Result.Error("Authentication token is missing!")
                }
            } catch (e: Exception) {
                Log.e("ProfileRequestError", "Unexpected error", e)
                _profileState.value = Result.Error("Unexpected Error: ${e.message}")
            }
        }
    }
}

