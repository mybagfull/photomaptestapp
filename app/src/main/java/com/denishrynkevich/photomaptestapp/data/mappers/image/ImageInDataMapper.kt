package com.denishrynkevich.photomaptestapp.data.mappers.image

import com.denishrynkevich.photomaptestapp.data.models.image.ImageRequest
import com.denishrynkevich.photomaptestapp.domain.model.ImageInData
import javax.inject.Inject

class ImageInDataMapper @Inject constructor() {

    operator fun invoke(
        response: ImageRequest
    ) : ImageInData = with(response) {
        return ImageInData(
            base64Image = base64Image.orEmpty(),
            date = date ?: 0,
            lat = lat ?: 0.0,
            lng = lng ?: 0.0
        )
    }
}