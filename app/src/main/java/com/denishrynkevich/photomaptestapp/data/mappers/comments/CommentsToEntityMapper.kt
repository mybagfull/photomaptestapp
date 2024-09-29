package com.denishrynkevich.photomaptestapp.data.mappers.comments

import com.denishrynkevich.photomaptestapp.data.database.CommentEntity
import com.denishrynkevich.photomaptestapp.domain.model.CommentOutData
import javax.inject.Inject

class CommentsToEntityMapper @Inject constructor() {

    operator fun invoke(
        comment: CommentOutData
    ): CommentEntity = with(comment) {
        return CommentEntity(
            id = id,
            date = date.toLong(),
            text = text
        )
    }
}