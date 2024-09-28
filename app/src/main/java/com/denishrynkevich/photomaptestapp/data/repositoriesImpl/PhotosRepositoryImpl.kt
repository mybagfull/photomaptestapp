package com.denishrynkevich.photomaptestapp.data.repositoriesImpl

import com.denishrynkevich.photomaptestapp.domain.model.PhotoInData
import com.denishrynkevich.photomaptestapp.domain.model.PhotoOutData
import com.denishrynkevich.photomaptestapp.domain.repositories.PhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
) : PhotosRepository {
    //TODO
    override suspend fun uploadPhoto(photoIn: PhotoInData): PhotoOutData {
        TODO("Not yet implemented")
    }

    override suspend fun getImages(page: Int): List<PhotoOutData> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteImage(photoId: Int): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getImageById(id: Int): PhotoOutData {
        TODO("Not yet implemented")
    }

    override fun getPagedPhotos(): Flow<List<PhotoOutData>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteImageFromDb(imageOut: PhotoOutData) {
        TODO("Not yet implemented")
    }

    override suspend fun getImageFromDb(): List<PhotoOutData> {
        TODO("Not yet implemented")
    }

    override suspend fun updateDb(image: List<PhotoOutData>) {
        TODO("Not yet implemented")
    }
}