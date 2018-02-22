package coding_trip.io.android.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.support.design.widget.AppBarLayout
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import coding_trip.io.android.R
import coding_trip.io.android.ui.home.page.GalleryPageFragment
import coding_trip.io.android.ui.home.page.ParticipantPageFragment
import coding_trip.io.android.ui.home.page.TimeLinePageFragment
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.os.Build
import android.view.View


class HomeActivity : AppCompatActivity() {

    private lateinit var toolBar: Toolbar
    private lateinit var appBar: AppBarLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var navigationView: NavigationView

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            findViewById<View>(android.R.id.content).systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        setContentView(R.layout.activity_home)

        toolBar = findViewById(R.id.toolbar)
        appBar = findViewById(R.id.appbar)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.viewpager)

        viewPager.adapter = TabsAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        navigationView = findViewById(R.id.main_navigation_view)
        initDrawer()
    }

    private fun initDrawer() {
        navigationView.apply {
            val header = navigationView.getHeaderView(0)
            val userProfileLoginText = header.findViewById<TextView>(R.id.user_profile_login_text)
            val userProfileImage = header.findViewById<ImageView>(R.id.user_profile_image)

            val user = auth.currentUser
            userProfileLoginText.text = user?.displayName
            GlideApp.with(this)
                    .load(user?.photoUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .circleCrop()
                    .into(userProfileImage)
        }
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> TODO()
                R.id.nav_history -> TODO()
                R.id.nav_profile -> TODO()
                R.id.nav_contributor -> TODO()
                R.id.nav_settings -> TODO()
                else -> TODO()
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(createIntent(context))
        }

        fun createIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    private class TabsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        companion object {
            private const val TAB_COUNT = 3
        }

        override fun getCount(): Int {
            return TAB_COUNT
        }

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> TimeLinePageFragment()
            1 -> ParticipantPageFragment()
            else -> GalleryPageFragment()
        }

        override fun getPageTitle(position: Int): String = when (position) {
            0 -> "タイムライン"
            1 -> "参加者"
            else -> "ギャラリー"
        }
    }
}
