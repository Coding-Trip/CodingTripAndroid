package coding_trip.io.android.di.ui.home.page.timeline

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.di.AppComponent
import coding_trip.io.android.di.ui.home.TimelineScope
import dagger.Component

@TimelineScope
@Component(dependencies = [AppComponent::class], modules = [TimelineModule::class])
interface TimelineComponent {

    object Initializer {
        fun init(): TimelineComponent =
                DaggerTimelineComponent.builder()
                        .appComponent(BaseApplication.appComponent)
                        .timelineModule(TimelineModule())
                        .build()
    }

}
