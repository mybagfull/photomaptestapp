package com.denishrynkevich.photomaptestapp.domain.model

data class PhotoOutData(
    val id: Int,
    val url: String,
    val date: String,
    val lat: Double,
    val lng: Double
)