package coding_trip.io.android.di.ui.start

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.di.AppComponent
import coding_trip.io.android.ui.start.StartStore
import dagger.Component

@StartScope
@Component(dependencies = [AppComponent::class], modules = [StartModule::class])
interface StartComponent {



    object Initializer {
        fun init(): StartComponent =
                DaggerStartComponent.builder()
                        .appComponent(BaseApplication.appComponent)
                        .startModule(StartModule())
                        .build()
    }

    fun inject(startStore: StartStore)
}
