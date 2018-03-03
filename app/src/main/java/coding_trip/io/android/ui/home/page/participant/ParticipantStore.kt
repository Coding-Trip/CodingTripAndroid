package coding_trip.io.android.ui.home.page.participant

import coding_trip.io.android.api.data.Participant
import coding_trip.io.android.api.data.User
import coding_trip.io.android.di.ui.home.page.participant.ParticipantComponent
import coding_trip.io.android.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ParticipantStore {

    @Inject
    lateinit var userRepository: UserRepository

    init {
        // DI
        ParticipantComponent.Initializer.init().inject(this)
    }

    fun participants(tripId: String): Observable<Participant> =
            userRepository.participants(tripId)
                    .observeOn(AndroidSchedulers.mainThread())

    fun user(userId: String): Observable<User> =
            userRepository.user(userId)
                    .observeOn(AndroidSchedulers.mainThread())
}
