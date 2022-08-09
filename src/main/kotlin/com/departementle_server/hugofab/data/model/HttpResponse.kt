package com.departementle_server.hugofab.data.model

sealed class HttpResponse<T>(val data: T? = null, val msg: String? = null) {
    class Success<T>(data: T?): HttpResponse<T>(data)
    class Error<T>(msg: String): HttpResponse<T>(null, msg)
}