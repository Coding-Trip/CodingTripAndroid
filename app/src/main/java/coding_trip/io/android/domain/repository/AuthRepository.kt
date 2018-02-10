package coding_trip.io.android.domain.repository

import android.content.Context
import androidx.content.edit
import coding_trip.io.android.BuildConfig
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request

class AuthRepository(
    private val context: Context
) {

    fun saveAccessToken(token: String) {
        val sharedPreferences =
            context.getSharedPreferences(ARG_PREFERENCE_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString(ARG_ACCESS_TOKEN, token)
        }
    }

    fun fetchAccessToken(code: String): Observable<String> {
        return this.fetch(code)
            .map { geneAuthDataFromResponse(it) }
    }

    private fun fetch(code: String): Observable<String> = Observable.create {
        val request = Request.Builder()
            .url("https://github.com/login/oauth/access_token" +
                "?code=$code&" +
                "client_id=${BuildConfig.CLIENT_ID}&" +
                "client_secret=${BuildConfig.CLIENT_SECRET}")
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .build()

        val response = okHttpClient.newCall(request).execute()
        when (response.code()) {
            in 300..399 -> it.onError(RuntimeException("Redirecting"))
            in 400..499 -> it.onError(RuntimeException("Network Exception"))
            in 500..599 -> it.onError(RuntimeException("Server Error"))
            else -> it.onNext(response.body()?.string().toString())
        }
        it.onComplete()
    }

    private fun geneAuthDataFromResponse(response: String): String {
        val str = response.split("&")
        return str[0].substring(13)
    }

    companion object {
        const val ARG_PREFERENCE_NAME = "GITHUB_CLIENT_PREFERENCE"

        const val ARG_ACCESS_TOKEN = "GITHUB_ACCESS_TOKEN"
    }
}