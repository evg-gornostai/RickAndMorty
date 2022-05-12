package com.gornostai.rickandmorty.di

import androidx.lifecycle.ViewModel
import com.gornostai.rickandmorty.presentation.screens.characterDetails.CharacterDetailsViewModel
import com.gornostai.rickandmorty.presentation.screens.characters.CharactersViewModel
import com.gornostai.rickandmorty.presentation.screens.episodeDetails.EpisodeDetailsViewModel
import com.gornostai.rickandmorty.presentation.screens.episodes.EpisodesViewModel
import com.gornostai.rickandmorty.presentation.screens.locationDetails.LocationDetailsViewModel
import com.gornostai.rickandmorty.presentation.screens.locations.LocationsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailsViewModel::class)
    fun bindCharacterDetailsViewModel(viewModel: CharacterDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EpisodeDetailsViewModel::class)
    fun bindEpisodeDetailsViewModel(viewModel: EpisodeDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EpisodesViewModel::class)
    fun bindEpisodesViewModel(viewModel: EpisodesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationDetailsViewModel::class)
    fun bindLocationDetailsViewModel(viewModel: LocationDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationsViewModel::class)
    fun bindLocationsViewModel(viewModel: LocationsViewModel): ViewModel

}