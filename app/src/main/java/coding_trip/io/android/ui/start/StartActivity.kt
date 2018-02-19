package coding_trip.io.android.ui.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.widget.Toast
import coding_trip.io.android.BuildConfig
import coding_trip.io.android.R
import coding_trip.io.android.domain.repository.AuthRepository
import coding_trip.io.android.ui.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GithubAuthProvider
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_start.*
import timber.log.Timber
import javax.inject.Inject

class StartActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user != null) {
            HomeActivity.start(this)
            finish()
        }

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

                    initFirebaseAuth(it)
                }, {
                    Timber.e("Failed to load access token")
                })
    }

    private fun initFirebaseAuth(token: String) {
        val credential = GithubAuthProvider.getCredential(token)
        auth.signInWithCredential(credential)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Timber.e("Failed to Sign in")
                        Toast.makeText(this, "Failed to Sign in", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Timber.e(it)
                }
    }

    companion object {
        const val SCOPE = "user"
    }
}
