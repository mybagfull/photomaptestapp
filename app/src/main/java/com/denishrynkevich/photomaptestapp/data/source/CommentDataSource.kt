package com.denishrynkevich.photomaptestapp.data.source

import com.denishrynkevich.photomaptestapp.data.database.CommentDao
import com.denishrynkevich.photomaptestapp.data.database.CommentEntity
import javax.inject.Inject

class CommentDataSource @Inject constructor(
    private val commentDao: CommentDao
) {

    suspend fun getCommentsFromDB(): List<CommentEntity> = commentDao.getCommentsFromDb()

    suspend fun insertComments(comments: List<CommentEntity>) = commentDao.addCommentsToDb(comments)

    suspend fun insertComment(comment: CommentEntity) = commentDao.addCommentToDb(comment)

    suspend fun deleteComment(comment: CommentEntity) = commentDao.deleteCommentFromDb(comment)
}