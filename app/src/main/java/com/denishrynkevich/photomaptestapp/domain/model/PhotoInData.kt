package com.denishrynkevich.photomaptestapp.domain.model

data class PhotoInData(
    val base64Image: String,
    val date: Long,
    val lat: Double,
    val lng: Double
)