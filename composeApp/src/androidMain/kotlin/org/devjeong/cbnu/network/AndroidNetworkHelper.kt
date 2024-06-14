package org.devjeong.cbnu.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import data.network.NetworkHelper

class AndroidNetworkHelper(context: Context) : NetworkHelper {
    private var networkCallback: ConnectivityManager.NetworkCallback? = null
    private val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun registerListener(onNetworkAvailable: () -> Unit, onNetworkLost: () -> Unit) {
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                onNetworkAvailable()
            }
            override fun onUnavailable() {
                onNetworkLost()
            }
            override fun onLost(network: Network) {
                onNetworkLost()
            }
        }
        networkCallback?.let { connectivityManager.registerDefaultNetworkCallback(it) }
    }

    override fun unregisterListener() {
        networkCallback?.let { connectivityManager.unregisterNetworkCallback(it) }
    }
}