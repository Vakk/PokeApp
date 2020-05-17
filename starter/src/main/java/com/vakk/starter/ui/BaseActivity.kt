package com.vakk.starter.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * This activity represents regular flow with ViewModel and ViewModelProvider.Factory provided by dagger 2.
 */
abstract class BaseActivity<VM : ViewModel>(
    protected open val viewModelClass: Class<VM>,
    @LayoutRes
    protected open val layoutId: Int
) : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM

    private var liveDataList = mutableListOf<LiveData<*>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        viewModel = initializeViewModel(viewModelClass)
    }


    override fun onResume() {
        super.onResume()
        liveDataList = mutableListOf()
        prepareViewModelObservers()
    }

    override fun onPause() {
        super.onPause()
        val oldLiveData = liveDataList.toList()
        val lifecycleOwner = this
        // Creation of new thread will speed up fragment recreation (f.e when user did rotation).
        Thread { oldLiveData.forEach { it.removeObservers(lifecycleOwner) } }.run()
    }

    /**
     * You can override this method for provide your own flow to create view model instance.
     */
    protected open fun initializeViewModel(viewModelClass: Class<VM>): VM {
        return ViewModelProvider(this, viewModelProviderFactory)[viewModelClass]
    }


    open fun prepareViewModelObservers() {

    }

    open fun <T> LiveData<T>.observe(observer: Observer<T>) {
        observeForever(observer)
        liveDataList.add(this)
    }
}