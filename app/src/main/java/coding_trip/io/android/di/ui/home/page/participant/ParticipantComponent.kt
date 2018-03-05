package coding_trip.io.android.di.ui.home.page.participant

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.di.AppComponent
import coding_trip.io.android.ui.home.page.participant.ParticipantStore
import dagger.Component

@ParticipantScope
@Component(dependencies = [AppComponent::class], modules = [ParticipantModule::class])
interface ParticipantComponent {


    object Initializer {
        fun init(): ParticipantComponent =
                DaggerParticipantComponent.builder()
                        .appComponent(BaseApplication.appComponent)
                        .participantModule(ParticipantModule())
                        .build()
    }

    fun inject(participantStore: ParticipantStore)
}
