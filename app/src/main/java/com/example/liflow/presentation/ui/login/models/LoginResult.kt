package com.example.liflow.presentation.ui.login.models

import com.example.liflow.presentation.ui.login.models.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)
