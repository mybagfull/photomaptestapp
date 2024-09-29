package com.denishrynkevich.photomaptestapp.data.models.comment

import com.squareup.moshi.Json

data class AddCommentRequest(
    @Json(name = "text") val text: String? = null
)
