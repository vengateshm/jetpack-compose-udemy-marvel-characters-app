package dev.vengateshm.marvelcharacterapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.vengateshm.marvelcharacterapp.model.api.ApiService
import dev.vengateshm.marvelcharacterapp.model.api.MarvelApiRepo

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
    @Provides
    fun provideApiRepo() = MarvelApiRepo(ApiService.api)
}