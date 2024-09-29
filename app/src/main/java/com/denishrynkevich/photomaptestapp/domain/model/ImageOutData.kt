package com.denishrynkevich.photomaptestapp.domain.model

data class ImageOutData(
    val id: Int,
    val url: String,
    val date: String,
    val lat: Double,
    val lng: Double
)
