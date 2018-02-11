package coding_trip.io.android.di

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.di.activity.StartActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    StartActivityBuilder::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: BaseApplication): Builder

        fun addNetworkModule(networkModule: NetworkModule): Builder
        fun addRepositoryModule(repositoryModule: RepositoryModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: BaseApplication)
}