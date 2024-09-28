package com.denishrynkevich.photomaptestapp.ui.token

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denishrynkevich.photomaptestapp.data.source.TokenDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TokenViewModel @Inject constructor(
    private val preferences: TokenDataSource
) : ViewModel() {
    private val _userIsAuthorizedLiveData = MutableLiveData<Boolean>()
    val userIsAuthorizedLiveData: LiveData<Boolean> get() = _userIsAuthorizedLiveData

    fun setTokens(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            preferences.setAccessToken(accessToken)
        }
        _userIsAuthorizedLiveData.value = true
    }

    fun isUserAuth() = preferences.getAccessToken().isEmpty()

    fun deleteTokens() {
        viewModelScope.launch(Dispatchers.IO) {
            preferences.deleteToken()
        }
    }
}