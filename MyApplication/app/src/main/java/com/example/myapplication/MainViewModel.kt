package com.example.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _info: MutableLiveData<Int> = MutableLiveData()
    val info: LiveData<Int> = _info

    init{
        Log.i("MainViewModel", "Created!")
        _info.value = 0
    }

    fun loadData(){
        _info.value = _info.value!! + 1
        //Log.i("MainViewModel", "current value: ${_info.value}")
    }
}