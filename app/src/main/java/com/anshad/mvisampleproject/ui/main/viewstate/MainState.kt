package com.anshad.mvisampleproject.ui.main.viewstate

import com.anshad.mvisampleproject.data.model.User

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()

}