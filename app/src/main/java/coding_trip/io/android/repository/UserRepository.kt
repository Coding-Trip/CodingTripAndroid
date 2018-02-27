package coding_trip.io.android.repository

import coding_trip.io.android.api.data.Participant
import coding_trip.io.android.api.data.User
import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class UserRepository {

    val database = FirebaseDatabase.getInstance()

    fun users(): Observable<User> =
            Observable.create<User> { emitter ->
                database.getReference("users").addChildEventListener(object : ChildEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        // no-op
                    }

                    override fun onChildMoved(dataSnapshot: DataSnapshot?, p1: String?) {
                        // no-op
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot?, p1: String?) {
                        // no-op
                    }

                    override fun onChildAdded(dataSnapshot: DataSnapshot?, p1: String?) {
                        dataSnapshot?.let {
                            it.getValue(User::class.java)?.let { user ->
                                emitter.onNext(user)
                            }
                        }
                    }

                    override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                        // no-op
                    }
                })
            }.subscribeOn(Schedulers.io())

    fun participants(tripId: String): Observable<Participant> =
            Observable.create<Participant> { emitter ->
                database.getReference("trips").child(tripId).child("participants").addChildEventListener(object : ChildEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        // no-op
                    }

                    override fun onChildMoved(dataSnapshot: DataSnapshot?, p1: String?) {
                        // no-op
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot?, p1: String?) {
                        // no-op
                    }

                    override fun onChildAdded(dataSnapshot: DataSnapshot?, p1: String?) {
                        dataSnapshot?.let {
                            it.getValue(Participant::class.java)?.let { participant ->
                                emitter.onNext(participant)
                            }
                        }
                    }

                    override fun onChildRemoved(p0: DataSnapshot?) {
                        // no-op
                    }
                })
            }

    fun user(userId: String): Observable<User> =
            Observable.create<User> { emitter ->
                database.getReference("users").child(userId).addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        // no-op
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        dataSnapshot?.let {
                            it.getValue(User::class.java)?.let { user ->
                                emitter.onNext(user)
                            }
                        }
                    }
                })
            }
}
