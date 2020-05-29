package com.vakk.starter.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * This fragment base class represents regular flow with ViewModel and ViewModelProvider.Factory provided by dagger 2.
 */
abstract class BaseFragment<VM : ViewModel>(
    protected open val viewModelClass: Class<VM>,
    @LayoutRes
    protected open val layoutId: Int
) : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM

    private var liveDataList = mutableListOf<LiveData<*>>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = initializeViewModel(viewModelClass)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        liveDataList = mutableListOf()
        prepareViewModelObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val oldLiveData = liveDataList.toList()
        val lifecycleOwner = this
        // Creation of new thread will speed up fragment recreation (f.e when user did rotation).
        Thread { oldLiveData.forEach { it.removeObservers(lifecycleOwner) } }.run()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(layoutId, container, false)
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
        observe(this@BaseFragment, observer)
        liveDataList.add(this)
    }
}