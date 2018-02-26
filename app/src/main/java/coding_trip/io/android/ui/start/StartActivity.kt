package coding_trip.io.android.ui.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.widget.Toast
import coding_trip.io.android.BuildConfig
import coding_trip.io.android.R
import coding_trip.io.android.ui.base.BaseActivity
import coding_trip.io.android.ui.home.HomeActivity
import timber.log.Timber

class StartActivity : BaseActivity(), StartFragment.ActivityBinder {

    companion object {
        private const val GITHUB_AUTH_URL =
                "https://github.com/login/oauth/authorize?client_id=${BuildConfig.CLIENT_ID}&scope=user"
    }

    private lateinit var store: StartStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        store = StartStore()

        if (store.isAlreadyLoggedIn()) {
            HomeActivity.start(this)
            finish()

            return
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, StartFragment.newInstance(this@StartActivity))
        }.commit()
    }

    override fun onNewIntent(intent: Intent) {
        if (intent.action != Intent.ACTION_VIEW) {
            return
        }

        intent.data?.let {
            getAccessToken(it.getQueryParameter("code"))
        } ?: throw RuntimeException("code must not be null")
    }

    private fun openCustomTabs() {
        CustomTabsIntent.Builder()
                .build()
                .launchUrl(this, Uri.parse(GITHUB_AUTH_URL))
    }

    private fun getAccessToken(code: String) {
        store.accessToken(code).subscribe({ token ->
            store.saveAccessToken(token).also {
                Timber.d("Token: ${token}")
            }

            startLogIn(token)
        }, {
            Timber.e("Failed to load access token")
        })
    }

    private fun startLogIn(token: String) {
        store.logIn(token).subscribe({
            HomeActivity.start(this)
        }, {
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
        })
    }

    /**
     * Callbacks [StartFragment.ActivityBinder]
     */
    override fun onLoginButtonClicked() {
        openCustomTabs()
    }
}
