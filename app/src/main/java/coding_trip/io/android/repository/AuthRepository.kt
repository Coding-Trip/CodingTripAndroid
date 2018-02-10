package coding_trip.io.android.repository

import coding_trip.io.android.datasource.AuthDataSource
import io.reactivex.Observable

class AuthRepository(
    private val authDataSource: AuthDataSource
) {

    fun saveAuthToken(token: String) {
        authDataSource.saveToken(token)
    }

    fun fetch(code: String): Observable<String> {
        return authDataSource.fetchAccessToken(code)
            .map { geneAuthDataFromResponse(it) }
    }

    private fun geneAuthDataFromResponse(response: String): String {
        val str = response.split("&")
        return str[0].substring(13)
    }
}