package hu.mobillab.antibore.ui

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.mobillab.antibore.interactors.OccupationInteractor
import hu.mobillab.antibore.network.OccupationApi
import hu.mobillab.antibore.ui.main.MainPresenter
import hu.mobillab.antibore.ui.occupation.OccupationPresenter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UIModule {

    @Singleton
    @Provides
    fun provideOccupationPresenter(occupationInteractor: OccupationInteractor) = OccupationPresenter(occupationInteractor)

    @Singleton
    @Provides
    fun provideMainPresenter(occupationInteractor: OccupationInteractor) = MainPresenter(occupationInteractor)

}