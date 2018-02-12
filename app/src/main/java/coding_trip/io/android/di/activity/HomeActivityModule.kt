package coding_trip.io.android.di.activity

import android.support.v7.app.AppCompatActivity
import coding_trip.io.android.ui.home.HomeActivity
import dagger.Binds
import dagger.Module

@Module
interface HomeActivityModule {
    @Binds
    fun providesAppCompatActivity(homeActivity: HomeActivity): AppCompatActivity
}
