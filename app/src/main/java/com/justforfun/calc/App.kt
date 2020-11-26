package com.justforfun.calc

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {

        /**
         * Gets application context
         *
         * @return Application context
         */
        @JvmStatic
        var context: Context? = null
            private set

        /**
         * Gets application instance
         *
         * @return Application instance
         */
        @JvmStatic
        fun instance(): App {
            return context as App
        }
    }
}