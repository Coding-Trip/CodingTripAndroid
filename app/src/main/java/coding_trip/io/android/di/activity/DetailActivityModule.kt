package coding_trip.io.android.di.activity

import android.support.v7.app.AppCompatActivity
import coding_trip.io.android.ui.detail.DetailActivity
import dagger.Binds
import dagger.Module

@Module
interface DetailActivityModule {
    @Binds
    fun providesAppCompatActivity(detailActivity: DetailActivity): AppCompatActivity
}
