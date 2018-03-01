package coding_trip.io.android.ui.home.page.participant

import timber.log.Timber

class ParticipantPresenter(private val fragment: ParticipantFragment) {

    private var store: ParticipantStore = ParticipantStore()

    fun fetchParticipants(tripId: String) {
        store.participants(tripId).subscribe({ participant ->
            participant.id?.let {
                fetchUser(it)
            }
        }, {
            Timber.e(it.localizedMessage)
        }, {
            // no-op
        })
    }

    private fun fetchUser(userId: String) {
        store.user(userId).subscribe({ user ->
            fragment.showParticipants(user)
        }, {
            Timber.e(it.localizedMessage)
        }, {
            // no-op
        })
    }
}
