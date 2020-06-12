package com.johnnylee.mvvm_arch_injection.application

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class MVVMArchInjectionApplicationManager: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}