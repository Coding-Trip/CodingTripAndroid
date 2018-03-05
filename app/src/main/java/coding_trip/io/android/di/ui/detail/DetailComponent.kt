package coding_trip.io.android.di.ui.detail

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.di.AppComponent
import dagger.Component

@DetailScope
@Component(dependencies = [AppComponent::class], modules = [DetailModule::class])
interface DetailComponent {

    object Initializer {
        fun init(): DetailComponent =
                DaggerDetailComponent.builder()
                        .appComponent(BaseApplication.appComponent)
                        .detailModule(DetailModule())
                        .build()
    }
}
