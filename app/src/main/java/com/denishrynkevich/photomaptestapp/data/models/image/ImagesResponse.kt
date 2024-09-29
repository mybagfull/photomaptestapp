package com.denishrynkevich.photomaptestapp.data.models.image

import com.squareup.moshi.Json

data class ImagesResponse(
    @Json(name = "status") val status: Int? = null,
    @Json(name = "data") val data: List<ImageResponse>? = null,
)