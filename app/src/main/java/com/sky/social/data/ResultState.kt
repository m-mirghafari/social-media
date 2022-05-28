package com.sky.social.data


sealed class ResultState<T> {
    data class Success<T>(val data: T?) : ResultState<T>()
    data class Error<T>(val error: Exception) : ResultState<T>()
    class Loading<T> : ResultState<T>()
}