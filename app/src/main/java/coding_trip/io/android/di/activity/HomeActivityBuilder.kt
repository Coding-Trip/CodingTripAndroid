package coding_trip.io.android.di.activity

import coding_trip.io.android.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface HomeActivityBuilder {
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    fun contributeHomeActivity(): HomeActivity
}
