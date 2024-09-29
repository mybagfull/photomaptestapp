package com.denishrynkevich.photomaptestapp.data.mappers.comments

import com.denishrynkevich.photomaptestapp.data.models.comment.CommentResponse
import com.denishrynkevich.photomaptestapp.domain.model.CommentOutData
import javax.inject.Inject

class CommentsResponseMapper @Inject constructor() {

    operator fun invoke(
        comment: CommentOutData
    ) : CommentResponse = with(comment) {
        return CommentResponse(
            id = id,
            date = date.toLong(),
            text = text
        )
    }
}