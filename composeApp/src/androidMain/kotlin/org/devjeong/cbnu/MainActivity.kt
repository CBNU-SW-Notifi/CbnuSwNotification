package org.devjeong.cbnu

import App
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import com.mmk.kmpnotifier.permission.permissionUtil
import org.devjeong.cbnu.network.AndroidNetworkMonitor
import org.lighthousegames.logging.logging

class MainActivity : ComponentActivity() {

    private lateinit var networkMonitor: AndroidNetworkMonitor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissionUtil by permissionUtil()
        networkMonitor = AndroidNetworkMonitor(this)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.WHITE, Color.WHITE
            )
        )

        NotifierManager.initialize(
            configuration = NotificationPlatformConfiguration.Android(
                notificationIconResId = R.drawable.ic_launcher_foreground,
                showPushNotification = true,
            )
        )

        permissionUtil.askNotificationPermission {
            logging().d { "HasNotification Permission: $it" }
        }

        setContent {
            App()
        }
    }
}