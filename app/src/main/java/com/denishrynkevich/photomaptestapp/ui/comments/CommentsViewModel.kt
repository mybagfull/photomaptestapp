package com.denishrynkevich.photomaptestapp.ui.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.denishrynkevich.photomaptestapp.data.source.CommentLoaderFactory
import com.denishrynkevich.photomaptestapp.domain.model.CommentOutData
import com.denishrynkevich.photomaptestapp.domain.repositories.CommentsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val commentsRepository: CommentsRepository,
    private val commentLoaderFactory: CommentLoaderFactory,

    ) : ViewModel() {


    private val _commentFlow: MutableSharedFlow<PagingData<CommentOutData>> = MutableSharedFlow()
    val commentFlow: SharedFlow<PagingData<CommentOutData>> get() = _commentFlow

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
    }

    fun getComments(imageId: Int) {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            commentLoaderFactory.getImages(imageId).collect {
                _commentFlow.emit(it)
            }
        }
    }

    fun sendComment(comment: String, imageId: Int) {
        viewModelScope.launch {
            commentsRepository.addComment(comment, imageId)
        }
    }

    fun deleteComment(imageId: Int, commentOutData: CommentOutData) {
        viewModelScope.launch {
            val response = commentsRepository.deleteComment(commentOutData.id, imageId)
            if (response.isSuccess) {
                deleteImageFromDb(commentOutData)
            }
        }
    }

    private fun deleteImageFromDb(commentOut: CommentOutData) {
        viewModelScope.launch(exceptionHandler) {
            commentsRepository.deleteCommentFromDb(commentOut)
        }

    }
}