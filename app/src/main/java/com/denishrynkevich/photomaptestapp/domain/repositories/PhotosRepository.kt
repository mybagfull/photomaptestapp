package com.denishrynkevich.photomaptestapp.domain.repositories

import com.denishrynkevich.photomaptestapp.domain.model.PhotoInData
import com.denishrynkevich.photomaptestapp.domain.model.PhotoOutData
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    suspend fun uploadPhoto(photoIn: PhotoInData): PhotoOutData

    suspend fun getImages(page: Int): List<PhotoOutData>

    suspend fun deleteImage(photoId: Int): Result<Unit>

    suspend fun getImageById(id: Int): PhotoOutData

    fun getPagedPhotos(): Flow<List<PhotoOutData>>

    suspend fun deleteImageFromDb(imageOut: PhotoOutData)
    suspend fun getImageFromDb(): List<PhotoOutData>
    suspend fun updateDb(image: List<PhotoOutData>)
}