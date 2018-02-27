package coding_trip.io.android.ui.home.page.timeline

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coding_trip.io.android.R

class TimeLineFragment : Fragment() {

    companion object {
        fun newInstance(): TimeLineFragment = TimeLineFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_timeline, container, false)
}
