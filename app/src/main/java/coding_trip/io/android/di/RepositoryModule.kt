package coding_trip.io.android.di

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.repository.AuthRepository
import coding_trip.io.android.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(application: BaseApplication): AuthRepository = AuthRepository(application)

    @Singleton
    @Provides
    fun provideUserRepository(): UserRepository = UserRepository()
}
