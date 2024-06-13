package org.devjeong.cbnu

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val dao = getJobHuntDatabase(applicationContext).postDetailDao()
        setContent {
            App()
        }
    }
}