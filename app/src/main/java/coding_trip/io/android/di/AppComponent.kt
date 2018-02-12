package coding_trip.io.android.di

import android.app.Application
import coding_trip.io.android.BaseApplication
import coding_trip.io.android.di.activity.DetailActivityBuilder
import coding_trip.io.android.di.activity.HomeActivityBuilder
import coding_trip.io.android.di.activity.StartActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    // base
    AppModule::class,
    InfraModule::class,
    RepositoryModule::class,
    // ui
    DetailActivityBuilder::class,
    HomeActivityBuilder::class,
    StartActivityBuilder::class
])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: BaseApplication)
}
