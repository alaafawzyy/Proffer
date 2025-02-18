package com.example.proffer.data.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest (
    @SerializedName("name")
    var name: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("account_type")
    var accountType: Int,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("password_confirmation")
    var confirmPassword: String
)

