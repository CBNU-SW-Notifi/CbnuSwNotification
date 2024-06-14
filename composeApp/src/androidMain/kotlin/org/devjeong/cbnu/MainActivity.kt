package org.devjeong.cbnu

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import presentation.components.NetworkAwareSnackBar
import org.devjeong.cbnu.network.AndroidNetworkMonitor

class MainActivity : ComponentActivity() {

    private lateinit var networkMonitor: AndroidNetworkMonitor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkMonitor = AndroidNetworkMonitor(this)

        setContent {
            NetworkAwareSnackBar(networkMonitor) {
                App()
            }
        }
    }
}