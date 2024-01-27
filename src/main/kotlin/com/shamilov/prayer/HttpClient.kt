package com.shamilov.prayer

import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

/**
 * @author Shamilov on 16.01.2024
 */

internal const val BASE_URL = "https://api.aladhan.com/v1/timingsByCity/%s?city=%s&country=%s"

internal val httpClient by lazy {
    OkHttpClient()
}

internal val json by lazy {
    Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}

internal fun Call.enqueue(
    onSuccess: (Response) -> Unit,
    onError: (Throwable) -> Unit,
) {
    val callback = object : Callback {
        override fun onFailure(call: Call, e: IOException) = onError(e)
        override fun onResponse(call: Call, response: Response) = onSuccess(response)
    }

    enqueue(callback)
}
