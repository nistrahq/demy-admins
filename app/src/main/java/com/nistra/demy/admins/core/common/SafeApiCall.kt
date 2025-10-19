package com.nistra.demy.admins.core.common

import retrofit2.HttpException
import java.io.IOException
import android.util.Log

/**
 * Executes a suspend API call safely, capturing and formatting any thrown exceptions.
 *
 * This helper centralizes network error handling by parsing known exceptions
 * (such as [IOException] or [HttpException]) into user-friendly messages
 * through [ErrorHandler]. Any unexpected errors are also caught and logged.
 *
 * **Usage example:**
 * ```
 * val result = safeApiCall { api.getUserProfile() }
 * ```
 *
 * If the API call fails, this function logs the error and rethrows an [Exception]
 * containing a descriptive message produced by [ErrorHandler].
 *
 * @param T The expected return type of the API call.
 * @param apiCall A suspend lambda representing the API request to execute.
 * @return The successful result of [apiCall] if no exception occurs.
 * @throws Exception Re-throws a formatted [Exception] when the call fails.
 * @see ErrorHandler For detailed parsing of HTTP and network exceptions.
 * @author Salim Ramirez
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> T): T {
    return try {
        apiCall()
    } catch (e: Exception) {
        val message = ErrorHandler.parseException(e)
        Log.e("safeApiCall", message, e)
        throw Exception(message)
    }
}
