package com.vakk.myapplication.ui.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.vakk.domain.entity.Language
import com.vakk.myapplication.R
import com.vakk.starter.ui.AdapterClickListener
import com.vakk.starter.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment<SettingsViewModel>(
    viewModelClass = SettingsViewModel::class.java,
    layoutId = R.layout.fragment_settings
), AdapterClickListener<Language> {

    val adapter by lazy { SettingsAdapter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareList()
    }

    private fun prepareList() {
        rvItems.adapter = adapter
    }

    override fun prepareViewModelObservers() {
        super.prepareViewModelObservers()
        viewModel.onLanguageChanged.observe(Observer {
            val activity = activity
            activity?.recreate()
            activity?.onBackPressed()
        })
        viewModel.onLanguagesLoaded.observe(Observer {
            adapter.replaceItems(it)
        })
    }

    override fun onClick(item: Language, view: View) {
        viewModel.changeLanguage(item)
    }
}