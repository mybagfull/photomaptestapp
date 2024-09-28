package com.denishrynkevich.photomaptestapp.util

interface CoroutinesErrorHandler {
    fun onError(message: String)
}