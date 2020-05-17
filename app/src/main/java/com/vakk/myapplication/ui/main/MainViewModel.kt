package com.vakk.myapplication.ui.main

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.vakk.common.cast
import javax.inject.Inject

class MainViewModel @Inject constructor(context: Context): AndroidViewModel(context.cast())