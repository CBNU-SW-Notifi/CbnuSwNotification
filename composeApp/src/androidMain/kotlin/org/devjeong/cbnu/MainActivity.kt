package org.devjeong.cbnu

import App
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.devjeong.cbnu.network.AndroidNetworkMonitor

class MainActivity : ComponentActivity() {

    private lateinit var networkMonitor: AndroidNetworkMonitor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkMonitor = AndroidNetworkMonitor(this)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.WHITE, Color.WHITE
            )
        )

        setContent {
            App()
        }
    }
}