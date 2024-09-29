package com.denishrynkevich.photomaptestapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "comments_table"
)
data class CommentEntity(
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val date: Long,
    @ColumnInfo val text: String
)
