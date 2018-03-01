package coding_trip.io.android.ui.home.page.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coding_trip.io.android.R

class GalleryFragment : Fragment() {

    companion object {
        fun newInstance(): GalleryFragment = GalleryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_gallery, container, false)

}
