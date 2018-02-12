package coding_trip.io.android.di

import android.content.Context
import coding_trip.io.android.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(context: Context): AuthRepository {
        return AuthRepository(context)
    }
}