package coding_trip.io.android.ui.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import coding_trip.io.android.BuildConfig
import coding_trip.io.android.R
import coding_trip.io.android.domain.repository.AuthRepository
import coding_trip.io.android.ui.home.HomeActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_start.*
import timber.log.Timber
import javax.inject.Inject

class StartActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

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
        val url = "https://github.com/login/oauth/authorize" +
                "?client_id=${BuildConfig.CLIENT_ID}&scope=$SCOPE"
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    private fun getAccessToken(code: String) {
        authRepository.fetchAccessToken(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authRepository.saveAccessToken(it)
                    Timber.d("Token: $it")

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }, {
                    Timber.e("Failed to load access token")
                })
    }

    companion object {
        const val SCOPE = "user"
    }
}
