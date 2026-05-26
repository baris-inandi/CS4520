package com.itsbaris.cs4520.a5_inandioglu_6696.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkReceiver(
    private val onNetworkChanged: (Boolean) -> Unit,
) : BroadcastReceiver() {
    /**
     * 1. What: Receives connectivity broadcasts and reports the current network state.
     * 2. Who: Called by Android after MainActivity dynamically registers this receiver.
     * 3. When: Executed whenever the system sends a connectivity change broadcast.
     */
    override fun onReceive(
        context: Context,
        intent: Intent?,
    ) {
        onNetworkChanged(isNetworkAvailable(context))
    }
}

/**
 * 1. What: Checks whether the device currently has an active internet-capable network.
 * 2. Who: Called by MainActivity and NetworkReceiver.
 * 3. When: Executed during startup and after connectivity change broadcasts.
 */
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(ConnectivityManager::class.java) ?: return false
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}
