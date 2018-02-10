package coding_trip.io.android.ui.home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import coding_trip.io.android.R
import coding_trip.io.android.ui.start.StartActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val firebaseUser = FirebaseAuth.getInstance().currentUser
        firebaseUser ?: kotlin.run {
            val startIntent = Intent(this, StartActivity::class.java)
            startActivity(startIntent)
        }
    }
}
