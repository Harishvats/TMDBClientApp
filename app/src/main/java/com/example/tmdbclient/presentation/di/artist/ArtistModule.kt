package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.domain.usecase.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtistUseCase
import com.example.tmdbclient.presentation.artist.ArtistVMFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ArtistModule {
    @ActivityScoped
    @Provides
    fun provideArtistViewModelFactory(
        getArtistUseCase: GetArtistUseCase, updateArtistUseCase: UpdateArtistUseCase
    ): ArtistVMFactory {
        return ArtistVMFactory(getArtistUseCase, updateArtistUseCase)
    }
}