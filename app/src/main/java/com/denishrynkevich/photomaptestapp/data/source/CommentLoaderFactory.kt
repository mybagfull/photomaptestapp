package com.denishrynkevich.photomaptestapp.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.denishrynkevich.photomaptestapp.domain.repositories.CommentsRepository
import javax.inject.Inject

class CommentLoaderFactory @Inject constructor(
    private val commentsRepository: CommentsRepository
) {

    private val PAGE_SIZE = 20

    fun getImages(id: Int) =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CommentPagingSource(commentsRepository, id) },
            initialKey = 0
        ).flow
}