package com.example.notes.presintation.feature.notes.di

import com.example.domain.feature.notes.usecase.AddNoteUseCase
import com.example.domain.feature.notes.usecase.DeleteNoteUseCase
import com.example.domain.feature.notes.usecase.GetNotesUseCase
import com.example.domain.feature.notes.usecase.UpdateNoteUseCase
import com.example.notes.presintation.feature.notes.viewmodel.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesModule = module {

    single { GetNotesUseCase(notesRepository = get()) }

    single { AddNoteUseCase(notesRepository = get()) }

    single { UpdateNoteUseCase(notesRepository = get()) }

    single { DeleteNoteUseCase(notesRepository = get()) }

    viewModel {
        NotesViewModel(getNotesUseCase = get(),
            addNoteUseCase = get(),
            updateNoteUseCase = get(), deleteNoteUseCase = get())
    }
}