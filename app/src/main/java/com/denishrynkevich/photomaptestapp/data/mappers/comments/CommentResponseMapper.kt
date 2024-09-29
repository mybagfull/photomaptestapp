package com.denishrynkevich.photomaptestapp.data.mappers.comments

import com.denishrynkevich.photomaptestapp.data.models.comment.CommentResponse
import com.denishrynkevich.photomaptestapp.domain.model.CommentOutData
import javax.inject.Inject

class CommentResponseMapper @Inject constructor() {

    operator fun invoke(
        response: CommentResponse
    ) : CommentOutData = with(response) {
        return CommentOutData(
            id = id ?: 0,
            date = date.toString().orEmpty(),
            text = text.orEmpty()
        )
    }
}