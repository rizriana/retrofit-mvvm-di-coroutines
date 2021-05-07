package id.rrlab.withkoin

import android.app.Application
import id.rrlab.withkoin.di.module.appModule
import id.rrlab.withkoin.di.module.repoModule
import id.rrlab.withkoin.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    repoModule,
                    viewModelModule
                )
            )
        }
    }
}