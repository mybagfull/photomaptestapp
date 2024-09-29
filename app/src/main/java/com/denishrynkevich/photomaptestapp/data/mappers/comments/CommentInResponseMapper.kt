package com.denishrynkevich.photomaptestapp.data.mappers.comments

import com.denishrynkevich.photomaptestapp.domain.model.CommentInData
import com.denishrynkevich.photomaptestapp.data.models.comment.AddCommentRequest
import javax.inject.Inject

class CommentInResponseMapper @Inject constructor() {

    operator fun invoke(
        commentIn: CommentInData
    ) : AddCommentRequest {
        return AddCommentRequest(
            text = commentIn.text
        )
    }
}