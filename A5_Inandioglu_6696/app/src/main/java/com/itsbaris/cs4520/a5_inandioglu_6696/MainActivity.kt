package com.itsbaris.cs4520.a5_inandioglu_6696

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.itsbaris.cs4520.a5_inandioglu_6696.navigation.TaskNavigation
import com.itsbaris.cs4520.a5_inandioglu_6696.notifications.TaskNotificationHelper
import com.itsbaris.cs4520.a5_inandioglu_6696.receiver.NetworkReceiver
import com.itsbaris.cs4520.a5_inandioglu_6696.receiver.isNetworkAvailable
import com.itsbaris.cs4520.a5_inandioglu_6696.ui.theme.A5_Inandioglu_6696Theme
import com.itsbaris.cs4520.a5_inandioglu_6696.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var networkReceiver: NetworkReceiver

    /**
     * 1. What: Sets the Compose content for the task manager application.
     * 2. Who: Called by the Android framework when MainActivity is created.
     * 3. When: Executed once during activity startup after process launch or recreation.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        TaskNotificationHelper.createNotificationChannel(this)
        setContent {
            A5_Inandioglu_6696Theme {
                TaskNavigation(taskViewModel)
            }
        }
    }

    /**
     * 1. What: Registers the network receiver and performs the initial sync status check.
     * 2. Who: Called by the Android framework when MainActivity becomes visible.
     * 3. When: Executed after onCreate and every time the activity returns to the foreground.
     */
    @Suppress("DEPRECATION")
    override fun onStart() {
        super.onStart()
        networkReceiver =
            NetworkReceiver { isConnected ->
                taskViewModel.updateSyncStatus(isConnected)
            }
        registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        taskViewModel.updateSyncStatus(isNetworkAvailable(this))
    }

    /**
     * 1. What: Unregisters the dynamically registered network receiver.
     * 2. Who: Called by the Android framework when MainActivity leaves the foreground.
     * 3. When: Executed before the activity is stopped.
     */
    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkReceiver)
    }
}
