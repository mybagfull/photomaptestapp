package com.denishrynkevich.photomaptestapp.util

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BaseRequest @Inject constructor() {
    private var dataFetchJob: Job? = null

    operator fun <T> invoke(
        liveData: MutableLiveData<T>,
        errorHandler: CoroutinesErrorHandler,
        viewModelScope: CoroutineScope,
        request: () -> Flow<T>
    ) {
        dataFetchJob = viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, error ->
            viewModelScope.launch(Dispatchers.Main) {
                errorHandler.onError(error.localizedMessage ?: "Error, try again.")
            }
        }) {
            request().collect {
                withContext(Dispatchers.Main) {
                    liveData.value = it
                }
            }
        }
    }
}