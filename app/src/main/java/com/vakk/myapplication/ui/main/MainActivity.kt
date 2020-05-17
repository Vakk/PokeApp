package com.vakk.myapplication.ui.main

import com.vakk.myapplication.R
import com.vakk.starter.ui.BaseActivity

class MainActivity : BaseActivity<MainViewModel>(
    MainViewModel::class.java,
    R.layout.activity_main
)
