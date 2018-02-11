package coding_trip.io.android.di.activity

import coding_trip.io.android.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface StartActivityBuilder {
    @ContributesAndroidInjector(
        modules = [
            StartActivityModule::class
        ]
    )
    fun contributeStartActivity(): StartActivity
}