package coding_trip.io.android.di

import coding_trip.io.android.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesStartActivityInjector(): StartActivity

}