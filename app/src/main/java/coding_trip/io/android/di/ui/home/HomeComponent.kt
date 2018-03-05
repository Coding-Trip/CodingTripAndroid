package coding_trip.io.android.di.ui.home

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.di.AppComponent
import dagger.Component

@HomeScope
@Component(dependencies = [AppComponent::class], modules = [HomeModule::class])
interface HomeComponent {

    object Initializer {
        fun init(): HomeComponent =
                DaggerHomeComponent.builder()
                        .appComponent(BaseApplication.appComponent)
                        .homeModule(HomeModule())
                        .build()
    }

}
