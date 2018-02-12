package coding_trip.io.android.di.activity

import coding_trip.io.android.ui.detail.DetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface DetailActivityBuilder {
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    fun contributeDetailActivity(): DetailActivity
}
