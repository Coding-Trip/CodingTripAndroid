package coding_trip.io.android.di.activity

import android.support.v7.app.AppCompatActivity
import coding_trip.io.android.ui.start.StartActivity
import dagger.Binds
import dagger.Module

@Module
interface StartActivityModule {
    @Binds
    fun providesAppCompatActivity(startActivity: StartActivity): AppCompatActivity
}
