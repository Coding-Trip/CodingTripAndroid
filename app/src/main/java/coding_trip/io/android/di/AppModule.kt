package coding_trip.io.android.di

import android.content.Context
import coding_trip.io.android.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: BaseApplication): Context = application
}