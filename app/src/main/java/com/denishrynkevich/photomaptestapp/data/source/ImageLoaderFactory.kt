package com.denishrynkevich.photomaptestapp.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.denishrynkevich.photomaptestapp.domain.repositories.PhotosRepository
import javax.inject.Inject

class ImageLoaderFactory @Inject constructor(
    private val photosRepository: PhotosRepository
) {

    private val PAGE_SIZE = 20

    fun getImages() =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingSource(photosRepository) },
            initialKey = 0
        ).flow
}