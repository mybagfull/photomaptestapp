package com.denishrynkevich.photomaptestapp.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData
import com.denishrynkevich.photomaptestapp.domain.repositories.PhotosRepository

class ImagePagingSource(
    private val photosRepository: PhotosRepository
) : PagingSource<Int, ImageOutData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageOutData> {
        return try {
            val pageIndex = params.key ?: 1
            val images = photosRepository.getImages(pageIndex)
            return LoadResult.Page(
                data = images,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (images.isEmpty()) null else pageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageOutData>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }
}