package coding_trip.io.android.ui.home

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import coding_trip.io.android.R
import coding_trip.io.android.ui.home.page.GalleryPageFragment
import coding_trip.io.android.ui.home.page.ParticipantPageFragment
import coding_trip.io.android.ui.home.page.TimeLinePageFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var toolBar: Toolbar
    private lateinit var appBar: AppBarLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolBar = findViewById(R.id.toolbar)
        appBar = findViewById(R.id.appbar)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.viewpager)

        viewPager.adapter = TabsAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
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
