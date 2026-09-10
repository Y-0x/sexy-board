package com.y0x.sexyboard.app

import android.app.Application
import com.y0x.sexyboard.BuildConfig
import com.y0x.sexyboard.app.di.appModule
import com.y0x.sexyboard.core.di.coreModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

/**
 * Root application class - plants the [Timber] debug tree and starts [Koin].
 */
class SexyBoardApp: Application() {
    val applicationScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(tree = Timber.DebugTree())
        }

        startKoin {
            androidContext(androidContext = this@SexyBoardApp)

            modules(
                appModule,
                coreModule
            )
        }
    }
}