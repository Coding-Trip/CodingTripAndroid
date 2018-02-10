package coding_trip.io.android.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GithubAuthProvider
import io.reactivex.Completable

class FirebaseDataSource {

    fun createFirebaseUser(token: String): Completable = Completable.create {
        val credential = GithubAuthProvider.getCredential(token)
        val auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful.not()) {
                    
                }
            }
            .addOnFailureListener {

            }
    }
}