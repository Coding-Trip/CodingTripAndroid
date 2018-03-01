package coding_trip.io.android.ui.home.page.participant

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coding_trip.io.android.R
import coding_trip.io.android.api.data.User

class ParticipantFragment : Fragment() {

    private lateinit var presenter: ParticipantPresenter

    companion object {
        fun newInstance(): ParticipantFragment = ParticipantFragment()

        // TODO : fix here
        const val TRIP_ID = "1"
    }

    private lateinit var recyclerView: RecyclerView
    private val adapter: ParticipantsAdapter = ParticipantsAdapter(mutableListOf())

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        presenter = ParticipantPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_participant, container, false).apply {
                recyclerView = findViewById(R.id.participants_list)
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            adapter = this@ParticipantFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        presenter.fetchParticipants(TRIP_ID)
    }

    fun showParticipants(user: User) {
        adapter.items.add(user)
        adapter.notifyDataSetChanged()
    }
}
