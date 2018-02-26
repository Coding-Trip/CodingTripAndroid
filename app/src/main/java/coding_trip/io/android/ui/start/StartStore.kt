package coding_trip.io.android.ui.start

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.repository.AuthRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StartStore {

    @Inject
    lateinit var authRepository: AuthRepository

    init {
        // DI
        BaseApplication.appComponent.inject(this)
    }

    fun isAlreadyLoggedIn(): Boolean = authRepository.isAlreadyLoggedIn()

    fun accessToken(code: String): Observable<String> =
            authRepository.fetchAccessToken(code)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    fun saveAccessToken(token: String) {
        authRepository.saveAccessToken(token)
    }

    fun logIn(token: String): Completable =
            authRepository.logIn(token)
}
