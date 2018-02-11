package coding_trip.io.android.di

import android.content.Context
import coding_trip.io.android.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideAuthRepository(context: Context): AuthRepository {
        return AuthRepository(context)
    }
}