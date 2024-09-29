package com.denishrynkevich.photomaptestapp.data.mappers.image

import com.denishrynkevich.photomaptestapp.data.database.ImageEntity
import com.denishrynkevich.photomaptestapp.data.models.image.ImageUploadResponse
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData
import javax.inject.Inject

class ImageOutDataMapper @Inject constructor() {

    operator fun invoke(
        imageEntity: ImageEntity
    ): ImageOutData = with(imageEntity) {
        return ImageOutData(
            id = id,
            url = url,
            date = date,
            lat = lat,
            lng = lng
        )
    }

    operator fun invoke(
        response: ImageUploadResponse
    ): ImageOutData {
        val image = response.data
        return ImageOutData(
            id = image?.id ?: 0,
            url = image?.url.orEmpty(),
            date = image?.date.toString().orEmpty(),
            lat = image?.lat ?: 0.0,
            lng = image?.lng ?: 0.0
        )
    }
}