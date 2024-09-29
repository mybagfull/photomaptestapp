package com.denishrynkevich.photomaptestapp.data.models.comment

import com.squareup.moshi.Json

data class CommentResponse(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "date") val date: Long? = null,
    @Json(name = "text") val text: String? = null
)
