package com.vakk.myapplication.ui.timer

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.lifecycle.Observer
import com.vakk.common.cast
import com.vakk.myapplication.CustomTimerAidlService
import com.vakk.myapplication.R
import com.vakk.myapplication.TestService
import com.vakk.starter.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : BaseFragment<TimerViewModel>(
    viewModelClass = TimerViewModel::class.java,
    layoutId = R.layout.fragment_timer
) {
    private var binder: CustomTimerAidlService? = null

    private var serviceConnection: ServiceConnection? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val serviceConnection = initServiceConnection()
        this.serviceConnection = serviceConnection
        val intent = Intent(context, TestService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
        context.bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
    }

    override fun onDetach() {
        super.onDetach()
        serviceConnection?.let { context?.unbindService(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnStart.setOnClickListener { binder?.restart() }
    }

    override fun prepareViewModelObservers() {
        super.prepareViewModelObservers()
        viewModel.onTick.observe(Observer {
            tvTime.text = binder?.totalDuration?.toString() ?: "0"
        })
    }

    private fun initServiceConnection() = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = CustomTimerAidlService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }
    }
}