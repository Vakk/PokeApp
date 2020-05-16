package com.vakk.starter.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.android.DaggerActivity

abstract class BaseActivity<VM: ViewModel>(
    protected open val viewModelClass: Class<VM>
) : DaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    protected open fun initializeViewModel(viewModelClass: Class<VM>): VM {

    }
}