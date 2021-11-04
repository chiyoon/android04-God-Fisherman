package com.android04.godfisherman.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {

    private val _firstRect = MutableLiveData<List<Int>?>()
    val firstRect : LiveData<List<Int>?> = _firstRect

    private val _secondRect = MutableLiveData<List<Int>?>()
    val secondRect : LiveData<List<Int>?> = _secondRect

    private val _bodySize = MutableLiveData<Float?>()
    val bodySize : LiveData<Float?> = _bodySize

    fun setRect(list: List<List<Int>>) {
        when {
            list.size >= 2 -> {
                _firstRect.value = list[0]
                _secondRect.value = list[1]
            }
            list.size == 1 -> {
                _firstRect.value = list[0]
                _secondRect.value = null
            }
            list.isEmpty() -> {
                _firstRect.value = null
                _secondRect.value = null
            }
        }
    }

    fun setSize(list: List<Int>) {
        if (list.size >= 2) {
            _bodySize.value = list[0].toFloat() / list[1]
        } else {
            _bodySize.value = null
        }
    }
}