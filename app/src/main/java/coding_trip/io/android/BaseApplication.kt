package coding_trip.io.android

import android.app.Application
import com.google.firebase.FirebaseApp

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupFirebase()
    }

    private fun setupFirebase() {
        FirebaseApp.initializeApp(this)
    }
}