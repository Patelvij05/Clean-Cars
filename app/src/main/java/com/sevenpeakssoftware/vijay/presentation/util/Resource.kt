package com.sevenpeakssoftware.vijay.presentation.util

//sealed class Resource<T>(
//    val status: Status,
//    val data: T? = null,
//    val error: Throwable? = null
//) {
//    class Success<T>(data: T) : Resource<T>(Status.SUCCESS, data)
//    class Loading<T>(data: T? = null) : Resource<T>(Status.LOADING, data)
//    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(Status.ERROR, data, throwable)
//}
//
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

class Resource<T> private constructor(val status: Status, val data: T?, val message: String?) {

    val isSuccess: Boolean
        get() = status === Status.SUCCESS && data != null

    val isLoading: Boolean
        get() = status === Status.LOADING

    val isLoaded: Boolean
        get() = status !== Status.LOADING

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}