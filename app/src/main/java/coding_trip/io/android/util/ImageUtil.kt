package coding_trip.io.android.util

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.setCircleImage(url: String?) =
        Glide.with(this.context).load(url).apply(RequestOptions().circleCrop()).into(this)

fun ImageView.setCircleImage(uri: Uri?) = this.setCircleImage(uri.toString())

