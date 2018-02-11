package coding_trip.io.android

import android.app.Activity
import android.app.Application
import coding_trip.io.android.di.DaggerAppComponent
import coding_trip.io.android.di.NetworkModule
import coding_trip.io.android.di.RepositoryModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class BaseApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setupDagger()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupDagger() {
        DaggerAppComponent.builder()
            .application(this)
            .addNetworkModule(NetworkModule())
            .addRepositoryModule(RepositoryModule())
            .build()
            .inject(this)
    }
}