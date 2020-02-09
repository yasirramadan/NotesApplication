package com.example.notes

import android.app.Application
import com.example.infrastructure.core.di.apiModule
import com.example.notes.di.appModule
import com.example.notes.presintation.feature.notes.di.notesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class NotesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()

        Timber.plant(Timber.DebugTree())

    }

    private fun initKoin() {
        startKoin {
            androidContext(this@NotesApp)
            modules(
                listOf(
                    appModule,
                    apiModule,
                    notesModule
                )
            )
        }
    }
}