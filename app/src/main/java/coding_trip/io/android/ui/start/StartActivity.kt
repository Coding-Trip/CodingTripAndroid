package coding_trip.io.android.ui.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import coding_trip.io.android.BuildConfig
import coding_trip.io.android.R
import coding_trip.io.android.datasource.AuthDataSource
import coding_trip.io.android.datasource.FirebaseDataSource
import coding_trip.io.android.repository.AuthRepository
import coding_trip.io.android.repository.FirebaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_start.login

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        login.setOnClickListener {
            openCustomTabs()
        }
    }

    override fun onNewIntent(intent: Intent) {
        val action = intent.action
        if (action != Intent.ACTION_VIEW) {
            return
        }
        val uri = intent.data
        uri?.let {
            val code = it.getQueryParameter("code")
            getAccessToken(code)
        } ?: throw RuntimeException("code must not be null")
    }

    private fun openCustomTabs() {
        val url = getString(R.string.str_oath_url, BuildConfig.CLIENT_ID, SCOPE)
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    private fun getAccessToken(code: String) {
        // this part may be replaced after implementing kodein
        val authDataSource = AuthDataSource(this)
        val authRepository = AuthRepository(authDataSource)

        authRepository.fetch(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authRepository.saveAuthToken(it)
            }, {
                TODO("This log implementation will be replaced by Timber")
                Log.e("StartActivity", "Failed to load access token")
            })
    }

    private fun createFirebaseUser(token: String) {
        // this part may be replaced after implementing kodein
        val firebaseDataSource = FirebaseDataSource()
        val firebaseRepository = FirebaseRepository(firebaseDataSource)

        firebaseRepository.updateProfileImageUrl()
    }

    companion object {
        const val SCOPE = "user"
    }
}
