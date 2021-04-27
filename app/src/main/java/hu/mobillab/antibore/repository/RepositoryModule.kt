package hu.mobillab.antibore.repository

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.mobillab.antibore.network.OccupationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAntiBoreDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AntiBoreDatabase::class.java,
        "antibore_database"
    ).build()

    @Singleton
    @Provides
    fun provideOccupationDao(db: AntiBoreDatabase): OccupationDAO = db.occupationDao()

    @Singleton
    @Provides
    fun provideOccupationRepository(occupationDao: OccupationDAO): OccupationRepository = OccupationRepository(occupationDao)
}