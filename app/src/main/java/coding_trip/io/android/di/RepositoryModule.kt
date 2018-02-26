package coding_trip.io.android.di

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.repository.AuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(application: BaseApplication): AuthRepository {
        return AuthRepository(application)
    }
}