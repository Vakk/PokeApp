package com.vakk.starter.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * This activity represents regular flow with ViewModel and ViewModelProvider.Factory provided by dagger 2.
 */
abstract class BaseActivity<VM : ViewModel>(
    protected open val viewModelClass: Class<VM>
) : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel(viewModelClass)
    }

    /**
     * You can override this method for provide your own flow to create view model instance.
     */
    protected open fun initializeViewModel(viewModelClass: Class<VM>): VM {
        return ViewModelProvider(this, viewModelProviderFactory)[viewModelClass]
    }
}