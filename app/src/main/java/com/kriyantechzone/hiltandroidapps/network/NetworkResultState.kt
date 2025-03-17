package com.kriyantechzone.hiltandroidapps.network

import java.lang.Exception

sealed class NetworkResultState<out R> {
    data class Success<out T>(val data:T):NetworkResultState<T>()
    data class Error(val msg:String, val exception: Exception):NetworkResultState<Nothing>()
    data object Loading:NetworkResultState<Nothing>()
}