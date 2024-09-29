package com.denishrynkevich.photomaptestapp.data.repositoriesImpl

import com.denishrynkevich.photomaptestapp.data.mappers.image.ImageOutDataMapper
import com.denishrynkevich.photomaptestapp.data.mappers.image.ImageInResponseMapper
import com.denishrynkevich.photomaptestapp.data.mappers.image.ImageMapper
import com.denishrynkevich.photomaptestapp.data.mappers.image.ImageEntityMapper
import com.denishrynkevich.photomaptestapp.data.network.service.ImageService
import com.denishrynkevich.photomaptestapp.data.source.ImageDataBaseSource
import com.denishrynkevich.photomaptestapp.domain.model.ImageInData
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData
import com.denishrynkevich.photomaptestapp.domain.repositories.PhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val imageDataBaseSource: ImageDataBaseSource,
    private val imageService: ImageService,
    private val imageOutDataMapper: ImageOutDataMapper,
    private val imageToEntityMapper: ImageEntityMapper,
    private val imageMapper: ImageMapper,
    private val imageInResponseMapper: ImageInResponseMapper,
) : PhotosRepository {

    override suspend fun uploadImage(imageIn: ImageInData): ImageOutData =
        withContext(Dispatchers.IO) {
            val uploadImageResponse = imageInResponseMapper(imageIn)
            val response = imageService.uploadImage(uploadImageResponse)
            val imageResp = response.body()
            imageOutDataMapper(imageResp ?: throw IllegalStateException("ImageOutResponse is null"))
        }

    override suspend fun getImages(page: Int): List<ImageOutData> {
        val response = imageService.getImage(page)
        if (response.isSuccessful) {
            val imageResponse = response.body()
            val images = imageMapper(imageResponse?.data.orEmpty())
            return images
        } else {
            return imageDataBaseSource.getImagesFromDataBase().map { imageOutDataMapper(it) }
        }
    }

    override suspend fun updateDb(image: List<ImageOutData>) {
        withContext(Dispatchers.IO) {
            imageDataBaseSource.addPhotos(image.map { imageToEntityMapper.invoke(it) })
        }
    }

    override suspend fun deleteImage(imageId: Int): Result<Unit> {
        return try {
            val response = imageService.deleteImage(imageId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete image"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getImageFromDb(): List<ImageOutData> {
        return (imageDataBaseSource.getImagesFromDataBase().map { imageOutDataMapper(it) })
    }

    override suspend fun getImageById(id: Int): ImageOutData = withContext(Dispatchers.IO) {
        imageOutDataMapper(imageDataBaseSource.getImageById(id))
    }

    override fun getPagedPhotos() =
        imageDataBaseSource.getPagedPhotos().map { it -> it.map { imageOutDataMapper(it) } }


    override suspend fun deleteImageFromDb(imageOut: ImageOutData) =
        withContext(Dispatchers.IO) {
            imageDataBaseSource.deleteImageFromDb(imageToEntityMapper(imageOut))
        }

}