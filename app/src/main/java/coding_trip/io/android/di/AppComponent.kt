package coding_trip.io.android.di

import coding_trip.io.android.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    InfraModule::class,
    RepositoryModule::class,
    ActivityModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: BaseApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: BaseApplication)
}