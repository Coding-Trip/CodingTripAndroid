package coding_trip.io.android

import android.app.Application
import coding_trip.io.android.di.AppComponent
import timber.log.Timber

class BaseApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = AppComponent.Initializer.init(this)

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}