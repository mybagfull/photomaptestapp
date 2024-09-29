package com.denishrynkevich.photomaptestapp.domain.repositories

import com.denishrynkevich.photomaptestapp.domain.model.ImageInData
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun uploadImage(imageIn: ImageInData): ImageOutData

    suspend fun getImages(page: Int): List<ImageOutData>

    suspend fun deleteImage(imageId: Int): Result<Unit>

    suspend fun getImageById(id: Int): ImageOutData

    fun getPagedPhotos(): Flow<List<ImageOutData>>

    suspend fun deleteImageFromDb(imageOut: ImageOutData)
    suspend fun getImageFromDb(): List<ImageOutData>
    suspend fun updateDb(image: List<ImageOutData>)
}