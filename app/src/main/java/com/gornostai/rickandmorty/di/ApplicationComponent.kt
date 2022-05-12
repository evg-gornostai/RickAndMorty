package com.gornostai.rickandmorty.di

import android.app.Application
import com.gornostai.rickandmorty.presentation.screens.characterDetails.CharacterDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.characters.CharactersFragment
import com.gornostai.rickandmorty.presentation.screens.episodeDetails.EpisodeDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.episodes.EpisodesFragment
import com.gornostai.rickandmorty.presentation.screens.locationDetails.LocationDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.locations.LocationsFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules =
    [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: CharacterDetailsFragment)

    fun inject(fragment: CharactersFragment)

    fun inject(fragment: EpisodeDetailsFragment)

    fun inject(fragment: EpisodesFragment)

    fun inject(fragment: LocationDetailsFragment)

    fun inject(fragment: LocationsFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}