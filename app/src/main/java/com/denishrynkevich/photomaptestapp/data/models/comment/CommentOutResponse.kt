package com.denishrynkevich.photomaptestapp.data.models.comment

import com.denishrynkevich.photomaptestapp.data.models.comment.CommentResponse
import com.squareup.moshi.Json

data class CommentOutResponse(
    @Json(name = "status") val status: Int? = null,
    @Json(name = "data") val data: CommentResponse
)
