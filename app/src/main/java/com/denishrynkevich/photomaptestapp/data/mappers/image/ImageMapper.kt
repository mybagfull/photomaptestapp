package com.denishrynkevich.photomaptestapp.data.mappers.image

import com.denishrynkevich.photomaptestapp.data.models.image.ImageResponse
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    operator fun invoke(listResponse: List<ImageResponse>): List<ImageOutData> {
        return listResponse.map { response ->
            ImageOutData(
                id = response .id ?: 0,
                url = response.url.orEmpty(),
                date = mapTimestampToDate(response.date!!),
                lat = response.lat ?: 0.0,
                lng = response.lng ?: 0.0
            )
        }
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)
    }
}