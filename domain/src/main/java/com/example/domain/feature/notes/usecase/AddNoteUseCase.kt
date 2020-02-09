package com.example.domain.feature.notes.usecase

import com.example.domain.feature.notes.repository.NotesRepository
import cz.eman.kaal.domain.result.Result
import cz.eman.kaal.domain.usecases.UseCaseResult

class AddNoteUseCase(private val notesRepository: NotesRepository) :
    UseCaseResult<Unit, String>() {

    override suspend fun doWork(params: String): Result<Unit> = notesRepository.addNote(title = params)
}