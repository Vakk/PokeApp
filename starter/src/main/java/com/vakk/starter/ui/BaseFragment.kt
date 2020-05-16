package com.vakk.starter.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * This fragment base class represents regular flow with ViewModel and ViewModelProvider.Factory provided by dagger 2.
 */
abstract class BaseFragment<VM : ViewModel>(
    protected open val viewModelClass: Class<VM>
) : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initializeViewModel(viewModelClass)
    }

    /**
     * You can override this method for provide your own flow to create view model instance.
     */
    protected open fun initializeViewModel(viewModelClass: Class<VM>): VM {
        return ViewModelProvider(this, viewModelProviderFactory)[viewModelClass]
    }
}