package com.shamilov.prayer.data

import com.shamilov.prayer.data.models.ResponseData
import kotlinx.serialization.decodeFromString
import okhttp3.Request

/**
 * @author Shamilov on 16.01.2024
 */
internal fun loadTimings(
    date: String,
    city: String,
    country: String,
    callback: (Result<ResponseData>) -> Unit,
) {
    val request = Request.Builder()
        .url(String.format(BASE_URL, date, city, country))
        .build()

    httpClient
        .newCall(request)
        .enqueue(
            onSuccess = {
                callback(Result.success(json.decodeFromString(it.body!!.string())))
            },
            onError = {
                callback(Result.failure(it))
            }
        )
}
