package com.nistra.demy.admins.core.common

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

/**
 * Utility object for handling and parsing exceptions from network operations.
 *
 * This object provides methods to interpret different types of exceptions,
 * particularly those related to HTTP requests, and convert them into user-friendly
 * error messages.
 *
 * @throws IOException for network-related issues.
 * @throws HttpException for HTTP protocol errors.
 * @author Salim Ramirez
 */
object ErrorHandler {
    fun parseException(exception: Throwable): String {
        return when (exception) {
            is IOException -> "Network error. Please check your internet connection."
            is HttpException -> parseHttpException(exception)
            else -> "An unexpected error occurred: ${exception.localizedMessage ?: exception.message}"
        }
    }

    private fun parseHttpException(exception: HttpException): String {
        val code = exception.code()
        val message = parseErrorBody(exception.response()?.errorBody())

        return when {
            message != null -> message
            code == 400 -> "Bad request. Please check your input."
            code == 401 -> "Unauthorized access. Please check your credentials."
            code == 403 -> "Forbidden access. You don't have permission to access this resource."
            code == 404 -> "Resource not found."
            code == 409 -> "Conflict error. The resource already exists."
            code >= 500 -> "Server error. Please try again later."
            else -> "HTTP error $code: ${exception.message()}"
        }
    }

    private fun parseErrorBody(errorBody: ResponseBody?): String? {
        return try {
            errorBody?.string()?.let {
                val json = JSONObject(it)
                json.optString("message", null)
                    ?: json.optString("error", null)
            }
        } catch (_: Exception) {
            null
        }
    }
}
