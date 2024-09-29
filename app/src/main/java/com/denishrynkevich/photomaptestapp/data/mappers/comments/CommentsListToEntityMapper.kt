package com.denishrynkevich.photomaptestapp.data.mappers.comments

import com.denishrynkevich.photomaptestapp.data.database.CommentEntity
import com.denishrynkevich.photomaptestapp.domain.model.CommentOutData
import javax.inject.Inject

class CommentsListToEntityMapper @Inject constructor() {

    operator fun invoke(
        comments: List<CommentOutData>
    ): List<CommentEntity> = comments.map {
        CommentEntity(
            id = it.id,
            date = it.date.toLong(),
            text = it.text
        )
    }
}