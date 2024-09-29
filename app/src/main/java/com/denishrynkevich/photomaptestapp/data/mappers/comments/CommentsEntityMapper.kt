package com.denishrynkevich.photomaptestapp.data.mappers.comments

import com.denishrynkevich.photomaptestapp.data.database.CommentEntity
import com.denishrynkevich.photomaptestapp.domain.model.CommentOutData

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CommentsEntityMapper @Inject constructor(){

    operator fun invoke(
        entity: CommentEntity
    ): CommentOutData = with(entity) {
        return CommentOutData(
            id = id,
            date = mapTimestampToDate(date),
            text = text
        )
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp)
        return SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(date)
    }
}