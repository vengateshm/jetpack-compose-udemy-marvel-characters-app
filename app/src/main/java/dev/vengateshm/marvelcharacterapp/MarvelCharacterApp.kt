package dev.vengateshm.marvelcharacterapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelCharacterApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}