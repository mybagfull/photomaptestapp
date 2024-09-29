package com.denishrynkevich.photomaptestapp.data.mappers.image

import com.denishrynkevich.photomaptestapp.data.models.image.ImageRequest
import com.denishrynkevich.photomaptestapp.domain.model.ImageInData
import javax.inject.Inject

class ImageInResponseMapper @Inject constructor() {

    operator fun invoke(
        imageIn: ImageInData
    ): ImageRequest = with(imageIn) {
        return ImageRequest(
            base64Image = base64Image,
            date = date,
            lat = lat,
            lng = lng
        )
    }
}