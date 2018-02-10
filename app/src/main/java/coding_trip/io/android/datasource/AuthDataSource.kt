package coding_trip.io.android.datasource

import android.content.Context
import androidx.content.edit
import coding_trip.io.android.BuildConfig
import coding_trip.io.android.R
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request

class AuthDataSource(
    private val context: Context
) {

    fun saveToken(token: String) {
        val sharedPreferences =
            context.getSharedPreferences(ARG_PREFERENCE_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString(ARG_ACCESS_TOKEN, token)
        }
    }

    fun fetchAccessToken(code: String): Observable<String> = Observable.create {
        val request = Request.Builder()
            .url(context.getString(R.string.str_access_token_url,
                code, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET))
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

    companion object {
        const val ARG_PREFERENCE_NAME = "GITHUB_CLIENT_PREFERENCE"

        const val ARG_ACCESS_TOKEN = "GITHUB_ACCESS_TOKEN"
    }
}