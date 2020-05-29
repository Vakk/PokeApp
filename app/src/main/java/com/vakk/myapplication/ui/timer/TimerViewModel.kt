package com.vakk.myapplication.ui.timer

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vakk.common.cast
import java.util.*
import javax.inject.Inject

class TimerViewModel @Inject constructor(
    context: Context
): AndroidViewModel(
    context.applicationContext.cast()
) {
    val onTick =  MutableLiveData<Any>()
    private val task = object: TimerTask() {
        override fun run() {
            onTick.postValue("")
        }
    }
    init {
        Timer().apply {
            scheduleAtFixedRate(task, 0, 500L)
        }
    }


}