package org.devjeong.cbnu

import android.app.Application
import org.devjeong.cbnu.platform.AndroidApplicationComponent
import di.initKoinAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        initKoinAndroid(appComponent = AndroidApplicationComponent()) {
            androidLogger()
            androidContext(this@MyApplication)
        }
    }

    companion object {
        lateinit var instance: MyApplication
            private set
    }
}