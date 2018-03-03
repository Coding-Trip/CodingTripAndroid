package coding_trip.io.android.ui.start

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coding_trip.io.android.R
import kotlinx.android.synthetic.main.fragment_start.*


class StartFragment : Fragment() {

    private lateinit var presenter: StartPresenter

    private var binder: ActivityBinder? = null

    /**
     * Listener to notify event to activity
     */
    interface ActivityBinder {

        fun onLoginButtonClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        presenter = StartPresenter(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_start, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_login.setOnClickListener {
            binder?.onLoginButtonClicked()
        }
    }

    companion object {
        fun newInstance(binder: ActivityBinder? = null): StartFragment = StartFragment().apply {
            this.binder = binder
        }
    }
}
