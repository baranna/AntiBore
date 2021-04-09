package hu.mobillab.antibore.interactors

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.mobillab.antibore.network.OccupationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Singleton
    @Provides
    fun provideOccupationInteractor(occupationApi: OccupationApi) = OccupationInteractor(occupationApi)
}