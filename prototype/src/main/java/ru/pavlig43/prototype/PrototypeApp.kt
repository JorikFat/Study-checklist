package ru.pavlig43.prototype

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.pavlig43.prototype.di.prototypeModule

class PrototypeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PrototypeApp)
            modules(prototypeModule)
        }

    }

}