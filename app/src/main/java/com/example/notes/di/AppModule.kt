package com.example.notes.di


import com.example.data.feature.common.repository.NotesRepositoryImp
import com.example.domain.feature.notes.repository.NotesRepository
import org.koin.dsl.module

val appModule = module {

    single<NotesRepository> {
        NotesRepositoryImp(notesRemoteDataSource = get(), notesLocalDataSource = get())
    }

}