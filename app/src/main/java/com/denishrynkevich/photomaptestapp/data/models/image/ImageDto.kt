package com.denishrynkevich.photomaptestapp.data.models.image

import com.squareup.moshi.Json

data class ImageDto(
    @Json(name = "ImageDtoIn") val imageDtoIn: ImageRequest? = null
)