package com.gornostai.rickandmorty.utils

import android.app.Application
import com.gornostai.rickandmorty.di.DaggerApplicationComponent

class App: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}