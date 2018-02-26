package coding_trip.io.android.di

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.ui.start.StartActivity
import coding_trip.io.android.ui.start.StartStore
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    // base
    AppModule::class,
    InfraModule::class,
    RepositoryModule::class
])
interface AppComponent {

    fun inject(application: BaseApplication)

    object Initializer {
        fun init(application: BaseApplication): AppComponent =
                DaggerAppComponent.builder()
                        .appModule(AppModule(application))
                        .repositoryModule(RepositoryModule())
                        .build()
    }

    // TODO : clean up here
    fun inject(startActivity: StartActivity)

    fun inject(startStore: StartStore)
}
