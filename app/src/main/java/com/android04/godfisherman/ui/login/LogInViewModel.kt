package com.android04.godfisherman.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android04.godfisherman.utils.SharedPreferenceManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val manager: SharedPreferenceManager) : ViewModel() {

    private val _isLogin: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val isLogin: LiveData<String> = _isLogin

    private val _isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchLoginData() {
        val idToken = manager.getString(LOGIN_ID_TOKEN)

        _isLogin.value = idToken
    }

    fun setLoginData(idToken: String) {
        manager.saveString(LOGIN_ID_TOKEN, idToken)
    }

    fun setUserInfo() {
        val auth = FirebaseAuth.getInstance().currentUser
        val name = auth?.displayName ?: ""
        val mail = auth?.email ?: ""
        val image = auth?.photoUrl?.toString() ?: ""

        manager.saveString(LOGIN_NAME, name)
        manager.saveString(LOGIN_EMAIL, mail)
        manager.saveString(LOGIN_IMG, image)
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    companion object {
        const val LOGIN_NAME = "LOGIN_NAME"
        const val LOGIN_EMAIL = "LOGIN_MAIL"
        const val LOGIN_IMG = "LOGIN_IMG"
        const val LOGIN_ID_TOKEN = "LOGIN_ID_TOKEN"
    }
}