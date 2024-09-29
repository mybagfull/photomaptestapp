package com.denishrynkevich.photomaptestapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images_table")
data class ImageEntity(
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val url: String,
    @ColumnInfo val date: String,
    @ColumnInfo val lat: Double,
    @ColumnInfo val lng: Double
)
