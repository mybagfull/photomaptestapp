package com.denishrynkevich.photomaptestapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments_table")
    suspend fun getCommentsFromDb(): List<CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCommentsToDb(comments: List<CommentEntity>)

    @Insert
    suspend fun addCommentToDb(comment: CommentEntity)

    @Delete
    suspend fun deleteCommentFromDb(commentEntity: CommentEntity)

}