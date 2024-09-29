package com.denishrynkevich.photomaptestapp.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.denishrynkevich.photomaptestapp.data.source.ImageLoaderFactory
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData
import com.denishrynkevich.photomaptestapp.domain.repositories.PhotosRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class PhotoViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
    private val imageLoaderFactory: ImageLoaderFactory
) : ViewModel() {

    private val _imageFlow: MutableSharedFlow<PagingData<ImageOutData>> = MutableSharedFlow()
    val imageFlow: SharedFlow<PagingData<ImageOutData>> get() = _imageFlow

    private val _deleteImageLiveData = MutableLiveData<Boolean>()
    val deleteImageLiveData: LiveData<Boolean> get() = _deleteImageLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
    }

    fun updateList() {
//        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
//            imageLoaderFactory.getImages().collect {
//                _imageFlow.emit(it)
//            }
//        }
    }

    fun deleteImage(imageId: Int, imageOut: ImageOutData) {
//        viewModelScope.launch {
//            try {
//                val response = photosRepository.deleteImage(imageId)
//
//                if (response.isSuccess) {
//                    deleteImageFromDb(imageOut)
//                    _deleteImageLiveData.value = response.isSuccess
//                } else {
//                    _deleteImageLiveData.value = response.isFailure
//                }
//            } catch (e: Exception) {
//                _deleteImageLiveData.value = false
//            }
//        }
    }

    private fun deleteImageFromDb(imageOut: ImageOutData) {
//        viewModelScope.launch(exceptionHandler) {
//            photosRepository.deleteImageFromDb(imageOut)
//
//        }

    }
}