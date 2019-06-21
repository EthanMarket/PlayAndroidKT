package com.ethan.core.api.base
import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class PlayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }

    companion object {
        var context: Context by Delegates.notNull()
            private set
    }


}