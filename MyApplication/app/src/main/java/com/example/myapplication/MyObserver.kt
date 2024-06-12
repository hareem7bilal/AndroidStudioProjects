package com.example.myapplication
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

class MyObserver: LifecycleObserver {
    annotation class OnLifecycleEvent(val value: Lifecycle.Event)

    companion object {
        private const val LOG_TAG = "MyObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connect(){
        Log.i(LOG_TAG, "On Resume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnect(){
        Log.i(LOG_TAG, "On Pause")
    }
}


