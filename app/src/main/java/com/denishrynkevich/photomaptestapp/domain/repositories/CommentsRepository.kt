package com.denishrynkevich.photomaptestapp.domain.repositories

import com.denishrynkevich.photomaptestapp.domain.model.CommentOutData

interface CommentsRepository {

    suspend fun getComments(imageId: Int, page: Int): List<CommentOutData>

    suspend fun deleteComment(commentId: Int, imageId: Int): Result<Unit>

    suspend fun addComment(commentIn: String, imageId: Int): CommentOutData

    suspend fun getCommentsFromDb(): List<CommentOutData>

    suspend fun insertComment(comment: CommentOutData)

    suspend fun insertComments(comment: List<CommentOutData>)

    suspend fun deleteCommentFromDb(comment: CommentOutData)
}