package coding_trip.io.android.ui.home.page.participant

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coding_trip.io.android.R
import coding_trip.io.android.api.data.User
import coding_trip.io.android.extension.setCircleImage
import coding_trip.io.android.extension.toGone
import coding_trip.io.android.extension.toVisible

class ParticipantsAdapter(val items: MutableList<User>) : RecyclerView.Adapter<ParticipantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_participant, parent, false)
        return ViewHolder(view = view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = items[position]

        holder.nameText.text = user.github_name
        holder.iconImage.setCircleImage(user.github_profile_url)

        // if it is last
        if (position == itemCount - 1) {
            holder.divider.toGone()
        } else {
            holder.divider.toVisible()
        }
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.name_text)
        val iconImage: ImageView = view.findViewById(R.id.icon_image)
        val divider: View = view.findViewById(R.id.divider_view)
    }
}