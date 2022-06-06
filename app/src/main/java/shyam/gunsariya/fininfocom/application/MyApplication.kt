package shyam.gunsariya.fininfocom.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import shyam.gunsariya.fininfocom.module.adapterModule
import shyam.gunsariya.fininfocom.module.repositoryModule
import shyam.gunsariya.fininfocom.module.viewModelModule

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@MyApplication)

            modules(listOf(viewModelModule, adapterModule,repositoryModule))
        }
    }
}