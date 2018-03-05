package coding_trip.io.android.di

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.repository.AuthRepository
import coding_trip.io.android.repository.UserRepository
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

    object Initializer {
        fun init(application: BaseApplication): AppComponent =
                DaggerAppComponent.builder()
                        .appModule(AppModule(application))
                        .repositoryModule(RepositoryModule())
                        .infraModule(InfraModule())
                        .build()
    }

    fun inject(application: BaseApplication)

    // export for useage from it's children

    fun authRepository(): AuthRepository
    fun userRepository(): UserRepository

}
