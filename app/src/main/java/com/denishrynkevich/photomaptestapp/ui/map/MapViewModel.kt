package com.denishrynkevich.photomaptestapp.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData
import com.denishrynkevich.photomaptestapp.domain.repositories.PhotosRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
) : ViewModel() {

    private val _imageListLiveData: MutableLiveData<List<ImageOutData>> = MutableLiveData()
    val imageListLiveData: LiveData<List<ImageOutData>> get() = _imageListLiveData


    fun getImageList() {
        viewModelScope.launch {
            _imageListLiveData.value = photosRepository.getImageFromDb()
        }
    }
}