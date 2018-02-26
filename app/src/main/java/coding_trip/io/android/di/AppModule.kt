package coding_trip.io.android.di

import coding_trip.io.android.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: BaseApplication) {

    @Provides
    @Singleton
    fun provideApplication(): BaseApplication = application
}
