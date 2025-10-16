package com.nistra.demy.admins.core.common

/**
 * Generic function to safely call an API and handle exceptions.
 *
 * @param apiCall A suspend function representing the API call.
 * @return A [Resource] object containing either the successful result or an error message.
 * @throws Exception if the API call fails.
 * @see Resource
 * @see ErrorHandler
 * @author Salim Ramirez
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(apiCall())
    } catch (e: Exception) {
        val message = ErrorHandler.parseException(e)
        Resource.Error(message)
    }
}
