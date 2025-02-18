package com.example.proffer.data.request

import com.google.gson.annotations.SerializedName

data class VerificationRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("code")
    var code: String,
)
